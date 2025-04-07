package com.zt.eweb.modular.codegen.client.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 模板组
 *
 * @author hccake
 * @date 2020-06-19 19:11:41
 */
@Data
@Schema(title = "模板组分页VO")
public class TemplateGroupPageVO {

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

	/**
	 * 名称
	 */
	@Schema(title = "名称")
	private String name;

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

	/**
	 * 创建时间
	 */
	@Schema(title = "创建时间")
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	@Schema(title = "修改时间")
	private LocalDateTime updateTime;

}