package com.zt.eweb.modular.codegen.infra.dal.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * software：IntelliJ IDEA 2022.2 class name: TypeScriptType description：前端和后端数据类型管理 the
 * entity object
 *
 * @author MoBaiJun 2022-07-01 14:27:10 <a href="https://www.mobaijun.com">框架师 Blog</a>
 */
@Getter
@Setter
@TableName("gen_type_script_type")
@Schema(title = "TypeScriptType", description = "前端和后端数据类型管理 entity object")
public class TypeScriptType {

	@TableId(value = "id", type = IdType.AUTO)
	@Schema(title = "id", description = "主键id")
	private Long id;

	@Schema(title = "groupKey", description = "模板组标识")
	private String groupKey;

	@Schema(title = "codeKey", description = "Java对应类型")
	private String codeKey;

	@Schema(title = "codeValue", description = "界面对应类型")
	private String codeValue;

	@Schema(title = "deleted", description = "逻辑删除（0：正常，1：删除）")
	private String deleted;

	@TableField(value = "create_time", fill = FieldFill.INSERT)
	@Schema(title = "createTime", description = "创建时间")
	private LocalDateTime createTime;

	@TableField(value = "update_time", fill = FieldFill.UPDATE)
	@Schema(title = "updateTime", description = "更新时间")
	private LocalDateTime updateTime;

}
