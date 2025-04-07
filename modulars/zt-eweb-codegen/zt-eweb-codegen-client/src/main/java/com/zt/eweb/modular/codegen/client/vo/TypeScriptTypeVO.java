package com.zt.eweb.modular.codegen.client.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * software：IntelliJ IDEA 2022.2 class name: TypeScriptTypeVO description：前端和后端数据类型管理 view
 * object
 *
 * @author MoBaiJun 2022-07-01 14:27:10
 */
@Getter
@Setter
@ToString
@Schema(title = "TypeScriptTypeVO", description = "前端和后端数据类型管理 view object")
public class TypeScriptTypeVO {

	@Schema(title = "id", description = "主键id")
	private Long id;

	@Schema(title = "groupKey", description = "模板组标识")
	private String groupKey;

	@Schema(title = "codeKey", description = "Java对应类型")
	private String codeKey;

	@Schema(title = "codeValue", description = "界面对应类型")
	private String codeValue;

	@Schema(title = "createTime", description = "创建时间")
	private LocalDateTime createTime;

	@Schema(title = "updateTime", description = "更新时间")
	private LocalDateTime updateTime;

}
