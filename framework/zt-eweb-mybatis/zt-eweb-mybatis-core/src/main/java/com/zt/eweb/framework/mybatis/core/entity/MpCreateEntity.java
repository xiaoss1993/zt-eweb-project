package com.zt.eweb.framework.mybatis.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

/**
 * 创建实体类 (不带逻辑删除)
 * 
 * @since 2022/7/26
 */
@Getter
@Setter
@FieldNameConstants(innerTypeName = "Create")
public abstract class MpCreateEntity extends MpIdEntity {

    /** 创建者ID */
    @TableField(fill = FieldFill.INSERT)
    private Long creator;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}
