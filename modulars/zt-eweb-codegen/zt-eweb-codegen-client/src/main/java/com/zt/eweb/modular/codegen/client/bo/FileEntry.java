package com.zt.eweb.modular.codegen.client.bo;

import com.zt.eweb.modular.codegen.common.constant.TemplateEntryTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 模板生成的文件项
 *
 * @author hccake
 */
@Data
public class FileEntry {

	/**
	 * ID
	 */
	@Schema(title = "ID")
	private String id;

	/**
	 * 文件名
	 */
	private String filename;

	/**
	 * 完全文件路径
	 */
	private String filePath;

	/**
	 * 父级的完全文件路径
	 */
	private String parentFilePath;

	/**
	 * 类型 1：文件夹 2：模板文件 3. 二进制文件
	 * @see com.hccake.ballcat.codegen.constant.TemplateEntryTypeEnum
	 */
	private TemplateEntryTypeEnum type;

	/**
	 * 文件内容
	 */
	@Schema(title = "文件内容")
	private byte[] fileContent;

}
