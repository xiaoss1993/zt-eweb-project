
package com.zt.eweb.framework.mybatis.core.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zt.eweb.framework.mybatis.core.binding.query.dynamic.DynamicSqlProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * 通用联表查询Mapper
 *
 * @author
 * @version 2018/12/22
 */
@Mapper
public interface DynamicQueryMapper {

  /**
   * 动态SQL查询
   *
   * @return
   */
  @SelectProvider(type = DynamicSqlProvider.class, method = "buildSqlForList")
  List<Map<String, Object>> queryForList(@Param(Constants.WRAPPER) QueryWrapper ew);

  /**
   * 动态SQL查询
   *
   * @param page
   * @return
   */
  @SelectProvider(type = DynamicSqlProvider.class, method = "buildSqlForListWithPage")
  IPage<Map<String, Object>> queryForListWithPage(Page<?> page,
      @Param(Constants.WRAPPER) QueryWrapper ew);

}