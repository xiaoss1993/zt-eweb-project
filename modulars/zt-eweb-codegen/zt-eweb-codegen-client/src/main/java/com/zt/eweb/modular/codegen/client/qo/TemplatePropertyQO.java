package com.zt.eweb.modular.codegen.client.qo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springdoc.api.annotations.ParameterObject;

/**
 * 模板属性配置 查询对象
 *
 * @author hccake 2020-06-22 15:46:39
 */
@Data
@Schema(title = "模板属性配置查询对象")
@ParameterObject
public class TemplatePropertyQO {

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@Schema(title = "ID")
	private Integer id;

	/**
	 * 模板组标识
	 */
	@Schema(title = "模板组标识")
	private String groupKey;

}