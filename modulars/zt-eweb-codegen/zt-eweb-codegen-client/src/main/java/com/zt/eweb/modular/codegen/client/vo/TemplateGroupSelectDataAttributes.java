package com.zt.eweb.modular.codegen.client.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author hccake
 */
@Data
@Schema(title = "模板分组选择数据属性")
public class TemplateGroupSelectDataAttributes {

	/**
	 * 图标（Base64）
	 */
	@Schema(title = "图标（Base64）")
	private String icon;

	/**
	 * 是否需要使用使用数据表
	 */
	@Schema(title = "是否需要使用使用数据表")
	private Integer useTable;

	/**
	 * 备注
	 */
	@Schema(title = "备注")
	private String remarks;

}
