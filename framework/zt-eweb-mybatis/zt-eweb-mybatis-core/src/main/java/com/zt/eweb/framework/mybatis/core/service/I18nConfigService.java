
package com.zt.eweb.framework.mybatis.core.service;

import com.zt.eweb.framework.mybatis.core.entity.I18nConfig;
import com.zt.eweb.framework.mybatis.core.vo.I18nConfigVO;
import com.zt.eweb.framework.mybatis.core.vo.Pagination;

import java.util.Collection;
import java.util.List;

/**
 * 国际化配置 Service接口
 *
 * @author wind
 * @version v3.0.0
 * @date 2022-10-12
 */
@Deprecated
public interface I18nConfigService extends BaseService<I18nConfig> {

  /**
   * 获取国际化配置列表
   *
   * @param entity     查询参数
   * @param pagination 分页
   * @return 国际化配置列表
   */
  Collection<List<I18nConfigVO>> getI18nList(I18nConfig entity, Pagination pagination);

  /**
   * 绑定国际化翻译内容
   *
   * @param voList              VO列表
   * @param getI18nCodeField    Get资源标识属性
   * @param setI18nContentField Set翻译内容属性
   */
  void bindI18nContent(List<?> voList, String getI18nCodeField, String setI18nContentField);

}