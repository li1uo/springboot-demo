/*
 *      Copyright (c) 2018-2028, DreamLu All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: DreamLu 卢春梦 (596392912@qq.com)
 */

package demo.springboot.config.ratelimit.redis;

import demo.springboot.boot.config.spel.MicaExpressionEvaluator;
import demo.springboot.tool.util.CharPool;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.expression.EvaluationContext;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * redis 限流
 *
 * @author L.cm
 */
@Aspect
@RequiredArgsConstructor
public class RedisRateLimiterAspect implements ApplicationContextAware {
	/**
	 * 表达式处理
	 */
	private final MicaExpressionEvaluator evaluator = new MicaExpressionEvaluator();
	/**
	 * redis 限流服务
	 */
	private final RedisRateLimiterClient rateLimiterClient;
	private ApplicationContext applicationContext;

	/**
	 * AOP 环切 注解 @RateLimiter
	 */
	@Around("@annotation(limiter)")
	public Object aroundRateLimiter(ProceedingJoinPoint point, RateLimiter limiter) throws Throwable {
		String limitKey = limiter.value();
		Assert.hasText(limitKey, "@RateLimiter value must have length; it must not be null or empty");
		// el 表达式
		String limitParam = limiter.param();
		// 表达式不为空
		String rateKey;
		if (StringUtils.isNotBlank(limitParam)) {
			String evalAsText = evalLimitParam(point, limitParam);
			rateKey = limitKey + CharPool.COLON + evalAsText;
		} else {
			rateKey = limitKey;
		}
		long max = limiter.max();
		long ttl = limiter.ttl();
		TimeUnit timeUnit = limiter.timeUnit();
		return rateLimiterClient.allow(rateKey, max, ttl, timeUnit, point::proceed);
	}

	/**
	 * 计算参数表达式
	 *
	 * @param point      ProceedingJoinPoint
	 * @param limitParam limitParam
	 * @return 结果
	 */
	private String evalLimitParam(ProceedingJoinPoint point, String limitParam) {
		MethodSignature ms = (MethodSignature) point.getSignature();
		Method method = ms.getMethod();
		Object[] args = point.getArgs();
		Object target = point.getTarget();
		Class<?> targetClass = target.getClass();
		EvaluationContext context = evaluator.createContext(method, args, target, targetClass, applicationContext);
		AnnotatedElementKey elementKey = new AnnotatedElementKey(method, targetClass);
		return evaluator.evalAsText(limitParam, elementKey, context);
	}

	@Override
	public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
