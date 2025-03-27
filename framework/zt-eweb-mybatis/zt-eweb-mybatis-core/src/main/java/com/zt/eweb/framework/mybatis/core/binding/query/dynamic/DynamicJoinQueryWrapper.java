
package com.zt.eweb.framework.mybatis.core.binding.query.dynamic;

import com.zt.eweb.framework.mybatis.core.binding.JoinsBinder;
import com.zt.eweb.framework.mybatis.core.binding.parser.ParserCache;
import com.zt.eweb.framework.mybatis.core.vo.Pagination;
import java.util.Collection;
import java.util.List;
import lombok.Getter;

/**
 * 动态查询wrapper
 *
 * @author Mazc@dibo.ltd
 * @version v2.0
 * @date 2020/04/16
 */
public class DynamicJoinQueryWrapper<DTO, T> extends ExtQueryWrapper<T> {

  /**
   * DTO类
   */
  @Getter
  private Class<DTO> dtoClass;
  /**
   * 字段
   */
  private Collection<String> fields;
  public DynamicJoinQueryWrapper(Class<DTO> dtoClass, Collection<String> fields) {
    this.dtoClass = dtoClass;
    this.fields = fields;
  }

  /**
   * dto字段和值
   */
  public List<AnnoJoiner> getAnnoJoiners() {
    return ParserCache.getAnnoJoiners(this.dtoClass, fields);
  }

  /**
   * 查询一条数据
   *
   * @param entityClazz
   * @return
   */
  @Override
  public T queryOne(Class<T> entityClazz) {
    return JoinsBinder.queryOne(this, entityClazz);
  }

  /**
   * 查询一条数据
   *
   * @param entityClazz
   * @return
   */
  @Override
  public List<T> queryList(Class<T> entityClazz) {
    return JoinsBinder.queryList(this, entityClazz);
  }

  /**
   * 查询一条数据
   *
   * @param entityClazz
   * @return
   */
  @Override
  public List<T> queryList(Class<T> entityClazz, Pagination pagination) {
    return JoinsBinder.queryList(this, entityClazz, pagination);
  }

}
