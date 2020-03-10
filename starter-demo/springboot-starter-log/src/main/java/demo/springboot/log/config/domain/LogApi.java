/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
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
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package demo.springboot.log.config.domain;

/**
 * 实体类
 *
 * @author Chill
 */
public class LogApi extends LogAbstract {

	private static final long serialVersionUID = 1L;

	/**
	 * 日志类型
	 */
	private String type;
	/**
	 * 日志标题
	 */
	private String title;
	/**
	 * 执行时间
	 */
	private String time;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "LogApi{" +
				"type='" + type + '\'' +
				", title='" + title + '\'' +
				", time='" + time + '\'' +
				", id=" + id +
				", serviceId='" + serviceId + '\'' +
				", serverIp='" + serverIp + '\'' +
				", serverHost='" + serverHost + '\'' +
				", env='" + env + '\'' +
				", remoteIp='" + remoteIp + '\'' +
				", userAgent='" + userAgent + '\'' +
				", requestUri='" + requestUri + '\'' +
				", method='" + method + '\'' +
				", methodClass='" + methodClass + '\'' +
				", methodName='" + methodName + '\'' +
				", params='" + params + '\'' +
				", createBy='" + createBy + '\'' +
				", createTime=" + createTime +
				'}';
	}
}
