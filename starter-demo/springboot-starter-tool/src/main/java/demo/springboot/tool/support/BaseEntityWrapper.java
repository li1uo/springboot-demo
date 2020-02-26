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
package demo.springboot.tool.support;


import java.util.List;
import java.util.stream.Collectors;

/**
 * 视图包装基类
 *
 * @author Chill
 */
public abstract class BaseEntityWrapper<E, V> {

	/**
	 * 单个实体类包装
	 *
	 * @param entity
	 * @return
	 */
	public abstract V entityVO(E entity);

	/**
	 * 实体类集合包装
	 *
	 * @param list
	 * @return
	 */
	public List<V> listVO(List<E> list) {
		return list.stream().map(this::entityVO).collect(Collectors.toList());
	}

}
