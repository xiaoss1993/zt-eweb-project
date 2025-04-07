package com.zt.eweb.modular.codegen.client.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.zt.eweb.modular.codegen.client.bo.ComponentOption;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 模板属性配置
 *
 * @author hccake
 * @date 2020-06-22 15:46:39
 */
@Data
@Schema(title = "模板属性配置")
public class TemplatePropertyPageVO {

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
	 * 标题
	 */
	@Schema(title = "标题")
	private String title;

	/**
	 * 属性键
	 */
	@Schema(title = "属性键")
	private String propKey;

	/**
	 * 默认值
	 */
	@Schema(title = "默认值")
	private String defaultValue;

	/**
	 * 前端显示的组件类型
	 */
	@Schema(title = "组件类型")
	private String componentType;

	/**
	 * 选择组件使用的选项
	 */
	@Schema(title = "组件选项")
	@TableField(typeHandler = JacksonTypeHandler.class)
	private List<ComponentOption> componentOptions;

	/**
	 * 必填，1：是，0：否
	 */
	@Schema(title = "必填，1：是，0：否")
	private Integer required;

	/**
	 * 排序值，越小越靠前
	 */
	@Schema(title = "排序值", description = "越小越靠前")
	private Integer orderValue;

	/**
	 * 备注信息
	 */
	@Schema(title = "备注信息")
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