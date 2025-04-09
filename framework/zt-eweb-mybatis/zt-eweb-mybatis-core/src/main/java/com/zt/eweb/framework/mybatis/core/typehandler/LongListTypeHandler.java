package com.zt.eweb.framework.mybatis.core.typehandler;

/**
 * 模块名 : 文件名 : 创建时间 : 2025/4/9 17:24 实现功能 :
 * <p>
 * 作者 : xiaoss 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ---------------------------------------------------------------- 修改记录 日 期     	版本     修改人
 * 修改内容 2025/4/9      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/**
 * List<Long> 的类型转换器类
 * @author xxm
 * @since 2023/8/8
 */
@MappedTypes(List.class)
@MappedJdbcTypes({JdbcType.VARCHAR,JdbcType.LONGVARCHAR})
public class LongListTypeHandler  extends AbstractJsonTypeHandler<List<Long>> {

  private static final String COMMA = ",";

  public LongListTypeHandler(Class<?> type) {
    super(type);
  }

  public LongListTypeHandler(Class<?> type, Field field) {
    super(type, field);
  }

  @Override
  public List<Long> parse(String value) {
    if (StrUtil.isNotBlank(value)){
      long[] longs = StrUtil.splitToLong(value, COMMA);
      return  Arrays.stream(longs).boxed().collect(Collectors.toList());
    }
    return null;
  }

  @Override
  public String toJson(List<Long> obj) {
      return CollUtil.join(obj, COMMA);
  }


}
