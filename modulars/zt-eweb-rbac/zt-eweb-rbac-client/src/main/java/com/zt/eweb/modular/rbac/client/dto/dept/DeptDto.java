package com.zt.eweb.modular.rbac.client.dto.dept;

import com.zt.eweb.framework.common.rest.dto.BaseDto;
import com.zt.eweb.modular.rbac.common.code.OrgCategoryCode;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author xxm
 * @since 2020/5/7 18:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Schema(title = "部门")
public class DeptDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = -4511914397757014519L;

    @Schema(description = "父机构ID")
    private Long parentId;

    @Schema(description = "名称")
    private String deptName;

    @Schema(description = "排序")
    private Double sortNo;

    /**
     * @see OrgCategoryCode
     */
    @Schema(description = "机构类别")
    private Integer orgCategory;

    @Schema(description = "机构编码")
    private String orgCode;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "传真")
    private String fax;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "备注")
    private String remark;

}
