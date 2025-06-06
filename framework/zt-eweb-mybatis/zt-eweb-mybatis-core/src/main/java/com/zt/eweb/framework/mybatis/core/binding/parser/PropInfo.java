package com.zt.eweb.framework.mybatis.core.binding.parser;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.zt.eweb.framework.mybatis.core.config.BaseConfig;
import com.zt.eweb.framework.mybatis.core.entity.BaseEntity;
import com.zt.eweb.framework.mybatis.core.entity.BaseModel;
import com.zt.eweb.framework.mybatis.core.util.BeanUtils;
import com.zt.eweb.framework.mybatis.core.util.S;
import com.zt.eweb.framework.mybatis.core.util.V;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bean相关信息缓存
 *
 * @author JerryMa
 * @version v2.2.1
 * @date 2021/4/20 Copyright © diboot.com
 */
@Slf4j
@Getter
public class PropInfo implements Serializable {

  private static final long serialVersionUID = 5921667308129991326L;
  /**
   * 列集合
   */
  private final List<String> columns = new ArrayList<>();
  /**
   * 字段-列的映射
   */
  private final Map<String, String> fieldToColumnMap = new LinkedCaseInsensitiveMap<>();
  /**
   * 列-字段的映射
   */
  private final Map<String, String> columnToFieldMap = new HashMap<>();
  /**
   * 列-字段类型的映射
   */
  private final Map<String, Class> columnToFieldTypeMap = new HashMap<>();
  /**
   * 自动更新字段列表
   */
  private final List<String> fillUpdateFieldList = new ArrayList<>();
  /**
   * 主键列
   */
  private String idColumn;
  /**
   * 主键字段类型
   */
  private Class<?> idFieldType;
  /**
   * 逻辑删除列
   */
  private String deletedColumn;

  /**
   * 初始化
   *
   * @param beanClass
   */
  public PropInfo(Class<?> beanClass) {
    this(beanClass, true);
  }

  /**
   * 初始化
   *
   * @param beanClass
   * @param isEntityClass 是否为entity实体类（数据库表对应实体）
   */
  public PropInfo(Class<?> beanClass, boolean isEntityClass) {
    List<Field> fields = BeanUtils.extractAllFields(beanClass, true);
    if (V.notEmpty(fields)) {
      for (Field fld : fields) {
        String fldName = fld.getName();
        String columnName = null;
        TableField tableField = fld.getAnnotation(TableField.class);
        if (tableField != null) {
          if (tableField.exist()) {
            if (V.notEmpty(tableField.value())) {
              columnName = tableField.value();
            } else {
              columnName = S.toSnakeCase(fldName);
            }
            FieldFill fill = tableField.fill();
            if (FieldFill.UPDATE.equals(fill) || FieldFill.INSERT_UPDATE.equals(fill)) {
              fillUpdateFieldList.add(fldName);
            }
          } else {
            continue;
          }
        }
        // 主键
        TableId tableId = fld.getAnnotation(TableId.class);
        if (tableId != null && this.idColumn == null) {
          if (V.notEmpty(tableId.value())) {
            columnName = tableId.value();
          } else if (columnName == null) {
            columnName = S.toSnakeCase(fldName);
          }
          this.idColumn = columnName;
          if (beanClass.isAssignableFrom(BaseEntity.class)) {
            this.idFieldType = Long.class;
          } else {
            this.idFieldType = fld.getType();
          }
        } else {
          TableLogic tableLogic = fld.getAnnotation(TableLogic.class);
          if (tableLogic != null) {
            if (columnName == null) {
              columnName = S.toSnakeCase(fldName);
            }
            this.deletedColumn = columnName;
            if (V.notEmpty(tableLogic.value())) {
              BaseConfig.setActiveFlagValue(tableLogic.value());
            }
          }
        }
        // 实体类基于默认规则提取列名
        if (columnName == null && isEntityClass) {
          columnName = S.toSnakeCase(fldName);
        }
        this.fieldToColumnMap.put(fldName, columnName);
        if (V.notEmpty(columnName)) {
          this.columnToFieldMap.put(columnName, fldName);
          if (columnName.equals(this.idColumn)) {
            if (beanClass.isAssignableFrom(BaseModel.class)) {
              this.columnToFieldTypeMap.put(columnName, String.class);
            } else {
              Class idType = BeanUtils.getGenericityClass(beanClass, 0);
              if (idType == null) {
                idType = fld.getType();
              }
              this.columnToFieldTypeMap.put(columnName, idType);
            }
          } else {
            this.columnToFieldTypeMap.put(columnName, fld.getType());
          }
          this.columns.add(columnName);
        }
      }
    }

  }

  /**
   * 根据列名获取字段
   *
   * @return
   */
  public String getFieldByColumn(String columnName) {
    if (V.isEmpty(this.columnToFieldMap)) {
      return null;
    }
    return this.columnToFieldMap.get(columnName);
  }

  /**
   * 根据列名获取字段
   *
   * @return
   */
  public String getColumnByField(String fieldName) {
    if (V.isEmpty(this.fieldToColumnMap)) {
      return null;
    }
    String column = this.fieldToColumnMap.get(fieldName);
    if (column == null) {
      log.debug("未找到字段 {} 对应的 列名", fieldName);
    }
    return column;
  }


  /**
   * 是否包含字段
   *
   * @return
   */
  public boolean containsField(String fieldName) {
    if (V.isEmpty(this.fieldToColumnMap)) {
      return false;
    }
    return this.fieldToColumnMap.containsKey(fieldName);
  }

  /**
   * 获取主键属性名
   *
   * @return
   */
  public String getIdFieldName() {
    if (V.isEmpty(this.columnToFieldMap)) {
      return null;
    }
    return this.columnToFieldMap.get(idColumn);
  }

  /**
   * 根据列名获取字段类型
   *
   * @return
   */
  public Class<?> getFieldTypeByColumn(String columnName) {
    if (V.isEmpty(this.columnToFieldTypeMap)) {
      return null;
    }
    return this.columnToFieldTypeMap.get(columnName);
  }

}
