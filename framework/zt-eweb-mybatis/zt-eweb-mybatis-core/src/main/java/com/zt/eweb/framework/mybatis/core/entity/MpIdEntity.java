package com.zt.eweb.framework.mybatis.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.OrderBy;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

/**
 * mybatis plus id实体
 *
 * 
 * @since 2021/8/17
 */
@Getter
@Setter
@FieldNameConstants(innerTypeName = "Id")
public abstract class MpIdEntity implements Serializable {

    private static final long serialVersionUID = 3982181843202226124L;

    @TableId(type = IdType.ASSIGN_ID)
    @OrderBy
    private Long id;

}
