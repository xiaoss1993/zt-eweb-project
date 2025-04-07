package com.zt.eweb.modular.codegen.client.qo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springdoc.api.annotations.ParameterObject;

/**
 * 数据源 查询对象
 *
 * @author hccake
 * @date 2020-06-17 10:24:47
 */
@Data
@Schema(title = "数据源查询对象")
@ParameterObject
public class DataSourceConfigQO {

	private static final long serialVersionUID = 1L;

	/**
	 * 数据源描述
	 */
	@Schema(title = "数据源描述")
	private String title;

}
