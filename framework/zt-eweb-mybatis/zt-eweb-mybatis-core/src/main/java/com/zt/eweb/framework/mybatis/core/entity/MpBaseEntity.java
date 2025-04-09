package com.zt.eweb.framework.mybatis.core.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

/**
 * 基础实体类 (带软删除)
 *
 * 
 * @since 2021/7/27
 */
@Getter
@Setter
@FieldNameConstants(innerTypeName = "Base")
public abstract class MpBaseEntity extends MpDelEntity implements Serializable {

    private static final long serialVersionUID = -2699324766101179583L;

    /** 删除标志 */
    @TableLogic
    private boolean deleted;

}
