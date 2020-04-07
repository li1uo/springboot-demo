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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 分页工具
 *
 * @author Chill
 */
@Data
@ApiModel(description = "查询条件")
public class Query {

	/**
	 * 当前页
	 */
	@ApiModelProperty(value = "当前页")
	private Integer current;

	/**
	 * 每页的数量
	 */
	@ApiModelProperty(value = "每页的数量")
	private Integer size;

	/**
	 * 返回Mybatis 分页参数
	 *
	 * @return
	 */
	public IPage build(){
		Page page = new Page(NumberUtils.toInt(String.valueOf(current), 1), NumberUtils.toInt(String.valueOf(size), 10));
		return page;
	}
}
