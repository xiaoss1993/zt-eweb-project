
package com.zt.eweb.framework.mybatis.core.service;

import com.zt.eweb.framework.mybatis.core.entity.Dictionary;
import com.zt.eweb.framework.mybatis.core.vo.DictionaryVO;
import com.zt.eweb.framework.mybatis.core.vo.LabelValue;

import java.io.Serializable;
import java.util.List;

/**
 * 数据字典Service
 *
 * @author
 * @version 2.0
 * @date 2019/01/01
 */
public interface DictionaryService extends BaseService<Dictionary> {

  /***
   * 获取对应类型的键值对
   * @param type
   * @return
   */
  List<LabelValue> getLabelValueList(String type);

  /**
   * 添加字典定义及其子项
   *
   * @param dictVO
   * @return
   */
  boolean createDictAndChildren(DictionaryVO dictVO);

  /**
   * 更新字典定义及其子项
   *
   * @param dictVO
   * @return
   */
  boolean updateDictAndChildren(DictionaryVO dictVO);

  /**
   * 删除字典定义及其子项
   *
   * @param id
   * @return
   */
  boolean deleteDictAndChildren(Serializable id);

}
