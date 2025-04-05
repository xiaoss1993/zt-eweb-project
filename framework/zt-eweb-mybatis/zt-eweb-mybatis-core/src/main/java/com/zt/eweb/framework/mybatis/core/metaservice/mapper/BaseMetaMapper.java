
package com.zt.eweb.framework.mybatis.core.metaservice.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.ResultHandler;

/**
 *  通用元数据操作接口
 *
 * @author
 * @version 2018/12/22
 */
@Mapper
public interface BaseMetaMapper extends BaseMapper {

  @Override
  int insert(Object entity);

  @Override
  int deleteById(Serializable id);

  @Override
  int deleteById(Object entity);

  @Override
  default int deleteByMap(Map columnMap) {
    return BaseMapper.super.deleteByMap(columnMap);
  }

  @Override
  int delete(Wrapper queryWrapper);

  @Override
  int deleteBatchIds(Collection idList);

  @Override
  int updateById(Object entity);

  @Override
  int update(Object entity, Wrapper updateWrapper);

  @Override
  default int update(Wrapper updateWrapper) {
    return BaseMapper.super.update(updateWrapper);
  }

  @Override
  Object selectById(Serializable id);

  @Override
  List selectBatchIds(Collection idList);

  @Override
  void selectBatchIds(Collection idList, ResultHandler resultHandler);

  @Override
  default List selectByMap(Map columnMap) {
    return BaseMapper.super.selectByMap(columnMap);
  }

  @Override
  default void selectByMap(Map columnMap, ResultHandler resultHandler) {
    BaseMapper.super.selectByMap(columnMap, resultHandler);
  }

  @Override
  default Object selectOne(Wrapper queryWrapper) {
    return BaseMapper.super.selectOne(queryWrapper);
  }

  @Override
  default Object selectOne(Wrapper queryWrapper, boolean throwEx) {
    return BaseMapper.super.selectOne(queryWrapper, throwEx);
  }

  @Override
  default boolean exists(Wrapper queryWrapper) {
    return BaseMapper.super.exists(queryWrapper);
  }

  @Override
  Long selectCount(Wrapper queryWrapper);

  @Override
  List selectList(Wrapper queryWrapper);

  @Override
  void selectList(Wrapper queryWrapper, ResultHandler resultHandler);

  @Override
  List selectList(IPage page, Wrapper queryWrapper);

  @Override
  void selectList(IPage page, Wrapper queryWrapper, ResultHandler resultHandler);

  @Override
  List<Map<String, Object>> selectMaps(Wrapper queryWrapper);

  @Override
  void selectMaps(Wrapper queryWrapper, ResultHandler resultHandler);

  @Override
  List<Map<String, Object>> selectMaps(IPage page, Wrapper queryWrapper);

  @Override
  void selectMaps(IPage page, Wrapper queryWrapper, ResultHandler resultHandler);

  @Override
  List selectObjs(Wrapper queryWrapper);

  @Override
  void selectObjs(Wrapper queryWrapper, ResultHandler resultHandler);

  @Override
  default IPage selectPage(IPage page, Wrapper queryWrapper) {
    return BaseMapper.super.selectPage(page, queryWrapper);
  }

  @Override
  default IPage<Map<String, Object>> selectMapsPage(IPage page, Wrapper queryWrapper) {
    return BaseMapper.super.selectMapsPage(page, queryWrapper);
  }
}