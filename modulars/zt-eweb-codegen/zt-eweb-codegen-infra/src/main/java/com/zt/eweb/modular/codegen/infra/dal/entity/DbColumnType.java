package com.zt.eweb.modular.codegen.infra.dal.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * software：IntelliJ IDEA 2022.1 class name: DbColumnType class description： 表字段类型
 *
 * @author MoBaiJun 2022/7/4 14:00
 */
@Getter
@Setter
public class DbColumnType {

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 包路径
	 */
	private String pkg;

}
