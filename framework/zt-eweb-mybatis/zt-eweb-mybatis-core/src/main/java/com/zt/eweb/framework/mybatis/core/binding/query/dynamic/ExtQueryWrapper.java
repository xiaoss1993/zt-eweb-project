
package com.zt.eweb.framework.mybatis.core.binding.query.dynamic;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zt.eweb.framework.mybatis.core.binding.helper.ServiceAdaptor;
import com.zt.eweb.framework.mybatis.core.binding.parser.ParserCache;
import com.zt.eweb.framework.mybatis.core.exception.InvalidUsageException;
import com.zt.eweb.framework.mybatis.core.util.ContextHolder;
import com.zt.eweb.framework.mybatis.core.vo.Pagination;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 动态查询wrapper
 *
 * @author Mazc@dibo.ltd
 * @version v2.0
 * @date 2020/04/16
 */
public class ExtQueryWrapper<T> extends QueryWrapper<T> {

  /**
   * 主实体class
   */
  @Getter
  @Setter
  private Class<T> mainEntityClass;

  /**
   * 获取entity表名
   *
   * @return
   */
  public String getEntityTable() {
    if (this.mainEntityClass == null) {
      this.mainEntityClass = getEntityClass();
    }
    return ParserCache.getEntityTableName(this.mainEntityClass);
  }

  /**
   * 查询一条数据
   *
   * @param entityClazz
   * @return
   */
  public T queryOne(Class<T> entityClazz) {
    this.mainEntityClass = entityClazz;
    IService<T> iService = ContextHolder.getIServiceByEntity(this.mainEntityClass);
    if (iService != null) {
      return ServiceAdaptor.getSingleEntity(iService, this);
    } else {
      throw new InvalidUsageException(
          "查询对象无BaseService/IService实现: " + this.mainEntityClass.getSimpleName());
    }
  }

  /**
   * 查询一条数据
   *
   * @param entityClazz
   * @return
   */
  public List<T> queryList(Class<T> entityClazz) {
    this.mainEntityClass = entityClazz;
    IService iService = ContextHolder.getIServiceByEntity(entityClazz);
    if (iService != null) {
      return ServiceAdaptor.queryList(iService, this);
    } else {
      throw new InvalidUsageException(
          "查询对象无BaseService/IService实现: " + entityClazz.getSimpleName());
    }
  }

  /**
   * 查询一条数据
   *
   * @param entityClazz
   * @return
   */
  public List queryList(Class<T> entityClazz, Pagination pagination) {
    this.mainEntityClass = entityClazz;
    IService iService = ContextHolder.getIServiceByEntity(entityClazz);
    if (iService != null) {
      return ServiceAdaptor.queryList(iService, this, pagination, entityClazz);
    } else {
      throw new InvalidUsageException(
          "查询对象无BaseService/IService实现: " + entityClazz.getSimpleName());
    }
  }

}
