package com.zt.eweb.modular.codegen.infra.dal.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 模板属性配置
 *
 * @author hccake 2020-06-22 15:46:39
 */
@Data
@TableName(value = "gen_template_property", autoResultMap = true)
@Schema(title = "模板属性配置")
public class TemplateProperty {

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
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
	 * 默认值(可为空值)
	 */
	@TableField(updateStrategy = FieldStrategy.IGNORED)
	@Schema(title = "默认值")
	private String defaultValue;

	/**
	 * 前端显示的组件类型
	 * @see com.hccake.ballcat.codegen.constant.ComponentTypeEnum
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
	@Schema(title = "必填", description = "1：是，0：否")
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
	 * 逻辑删除
	 */
	@TableLogic
	@TableField(fill = FieldFill.INSERT)
	@Schema(title = "逻辑删除")
	private Long deleted;

	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	@Schema(title = "创建时间")
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	@Schema(title = "修改时间")
	private LocalDateTime updateTime;

}
