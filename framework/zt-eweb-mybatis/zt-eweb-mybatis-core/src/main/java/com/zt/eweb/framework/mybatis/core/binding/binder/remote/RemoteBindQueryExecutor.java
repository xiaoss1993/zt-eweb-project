
package com.zt.eweb.framework.mybatis.core.binding.binder.remote;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zt.eweb.framework.mybatis.core.binding.cache.BindingCacheManager;
import com.zt.eweb.framework.mybatis.core.binding.helper.WrapperHelper;
import com.zt.eweb.framework.mybatis.core.binding.parser.PropInfo;
import com.zt.eweb.framework.mybatis.core.config.BaseConfig;
import com.zt.eweb.framework.mybatis.core.service.BaseService;
import com.zt.eweb.framework.mybatis.core.util.BeanUtils;
import com.zt.eweb.framework.mybatis.core.util.ContextHolder;
import com.zt.eweb.framework.mybatis.core.util.JSON;
import com.zt.eweb.framework.mybatis.core.util.V;
import com.zt.eweb.framework.mybatis.core.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 远程绑定查询执行器
 *
 * @author JerryMa
 * @version v2.4.0
 * @date 2021/11/3 Copyright © diboot.com
 */
@Slf4j
public class RemoteBindQueryExecutor {

  /**
   * 执行查询返回绑定数据
   *
   * @param remoteBindDTO
   * @return
   * @throws Exception
   */
  public static JsonResult execute(RemoteBindDTO remoteBindDTO) {
    Class entityClass;
    try {
      entityClass = Class.forName(remoteBindDTO.getEntityClassName());
    } catch (Exception e) {
      log.error("无法找到Entity类: {}", remoteBindDTO.getEntityClassName(), e);
      return JsonResult.FAIL_INVALID_PARAM(
          "模块下无Entity类: " + remoteBindDTO.getEntityClassName());
    }
    Collection<?> inConditionValues = remoteBindDTO.getInConditionValues();
    if (inConditionValues == null) {
      return JsonResult.OK();
    }
    if (inConditionValues.isEmpty()) {
      return JsonResult.OK(Collections.emptyList());
    }
    // 关联主键列名
    String refJoinCol = remoteBindDTO.getRefJoinCol();
    PropInfo propInfo = BindingCacheManager.getPropInfoByClass(entityClass);
    Class idFieldType = propInfo.getFieldTypeByColumn(refJoinCol);
    Collection<?> formatInValues = BeanUtils.convertIdValuesToType(inConditionValues, idFieldType);
    // 构建queryWrapper
    QueryWrapper<?> queryWrapper = new QueryWrapper<>();
    queryWrapper.setEntityClass(entityClass);
    if (V.notEmpty(remoteBindDTO.getSelectColumns())) {
      queryWrapper.select(remoteBindDTO.getSelectColumns());
    }
    // 构建查询条件
    queryWrapper.in(refJoinCol, formatInValues);
    queryWrapper.and(V.notEmpty(remoteBindDTO.getAdditionalConditions()),
        e -> remoteBindDTO.getAdditionalConditions().forEach(e::apply));
    // 排序
    WrapperHelper.buildOrderBy(queryWrapper, remoteBindDTO.getOrderBy(), e -> e);
    // 执行查询返回结果List
    try {
      String jsonStr = null;
      if ("Map".equals(remoteBindDTO.getResultType())) {
        List<Map<String, Object>> resultMap = getMapList(entityClass, queryWrapper);
        jsonStr = JSON.stringify(resultMap);
      } else if ("Entity".equals(remoteBindDTO.getResultType())) {
        List<?> resultList = getEntityList(entityClass, queryWrapper);
        jsonStr = JSON.stringify(resultList);
      }
      return JsonResult.OK(jsonStr);
    } catch (Exception e) {
      log.error("绑定查询执行异常", e);
      return JsonResult.FAIL_EXCEPTION("绑定查询执行异常: " + e.getMessage());
    }
  }

  /**
   * 获取Map结果
   *
   * @param queryWrapper
   * @return
   */
  private static List<Map<String, Object>> getMapList(Class entityClass, Wrapper queryWrapper) {
    IService referencedService = ContextHolder.getIServiceByEntity(entityClass);
    if (referencedService instanceof BaseService) {
      return ((BaseService) referencedService).getMapList(queryWrapper);
    } else {
      List<Map<String, Object>> list = referencedService.listMaps(queryWrapper);
      return checkedList(list);
    }
  }

  /**
   * 获取EntityList
   *
   * @param queryWrapper
   * @return
   */
  private static <T> List<T> getEntityList(Class entityClass, Wrapper queryWrapper) {
    IService referencedService = ContextHolder.getIServiceByEntity(entityClass);
    if (referencedService instanceof BaseService) {
      return ((BaseService) referencedService).getEntityList(queryWrapper);
    } else {
      List<T> list = referencedService.list(queryWrapper);
      return checkedList(list);
    }
  }

  /**
   * 检查list，结果过多打印warn
   *
   * @param list
   * @return
   */
  private static List checkedList(List list) {
    if (list == null) {
      list = Collections.emptyList();
    } else if (list.size() > BaseConfig.getBatchSize()) {
      log.warn("单次查询记录数量过大，返回结果数={}，请检查！", list.size());
    }
    return list;
  }

}
