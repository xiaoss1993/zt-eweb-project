
package com.zt.eweb.framework.mybatis.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zt.eweb.framework.mybatis.core.binding.query.BindQuery;
import com.zt.eweb.framework.mybatis.core.binding.query.Comparison;
import com.zt.eweb.framework.mybatis.core.vo.LabelValue;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 数据字典实体
 *
 * @author
 * @version v2.0
 * @date 2018/12/27
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName(value = "dbt_dictionary", autoResultMap = true)
public class Dictionary extends BaseEntity<String> {

  private static final long serialVersionUID = 11301L;

  /**
   * 租户ID
   */
  @JsonIgnore
  @TableField
  private String tenantId;

  /***
   * 上级ID
   */
  @TableField
  private String parentId;

  /**
   * 应用模块
   */
  @TableField
  private String appModule;

  /***
   * 数据字典类型
   */
  @NotNull(message = "数据字典类型不能为空！")
  @Length(max = 50, message = "数据字典类型长度超长！")
  @TableField
  private String type;

  /***
   * 数据字典项的显示名称
   */
  @NotNull(message = "数据字典项名称不能为空！")
  @Length(max = 100, message = "数据字典项名称长度超长！")
  @BindQuery(comparison = Comparison.LIKE)
  // @BindI18n("itemNameI18n")
  private String itemName;

  /**
   * 数据字典项的显示名称国际化资源标识
   */
  private String itemNameI18n;

  /***
   * 数据字典项的存储值（编码）
   */
  @Length(max = 100, message = "数据字典项编码长度超长！")
  @TableField
  private String itemValue;

  /***
   * 备注信息
   */
  @Length(max = 200, message = "数据字典备注长度超长！")
  @TableField
  private String description;

  /***
   * 排序号
   */
  @TableField
  private Integer sortId;

  /***
   * 是否为系统预置（预置不可删除）
   */
  @TableField("is_deletable")
  private Boolean isDeletable;

  /***
   * 是否可编辑
   */
  @TableField("is_editable")
  private Boolean isEditable;

  /**
   * 扩展字段
   */
  @TableField(typeHandler = JacksonTypeHandler.class)
  private Map<String, Object> extension;

  /***
   * 从extdata JSON中提取扩展属性值
   * @param extAttrName
   * @return
   */
  public Object getFromExt(String extAttrName) {
    if (this.extension == null) {
      return null;
    }
    return this.extension.get(extAttrName);
  }

  /***
   * 添加扩展属性和值到extdata JSON中
   * @param extAttrName
   * @param extAttrValue
   */
  public Dictionary addIntoExt(String extAttrName, Object extAttrValue) {
    if (extAttrName == null && extAttrValue == null) {
      return this;
    }
    if (this.extension == null) {
      this.extension = new LinkedHashMap<>();
    }
    this.extension.put(extAttrName, extAttrValue);
    return this;
  }

  /**
   * 转换为选项
   *
   * @return
   */
  public LabelValue toLabelValue() {
    return new LabelValue(this.getItemName(), this.getItemValue()).setExt(this.getExtension());
  }

}
