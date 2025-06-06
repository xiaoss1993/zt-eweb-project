
package com.zt.eweb.framework.mybatis.core.config;

import com.zt.eweb.framework.mybatis.core.util.PropertiesUtils;
import com.zt.eweb.framework.mybatis.core.util.S;
import com.zt.eweb.framework.mybatis.core.util.V;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 系统默认配置
 * @author
 * @version 2.0
 * @date 2019/01/01
 */
public class BaseConfig {

  private static final Logger log = LoggerFactory.getLogger(BaseConfig.class);
  private static Integer cutLength = null;
  private static Integer pageSize = null;
  private static Integer batchSize = null;
  private static Object ACTIVE_FLAG_VALUE = null;
  private static Long workerId = null, dataCenterId = null;

  /**
   * 从当前配置文件获取配置参数值
   *
   * @param key
   * @return
   */
  public static String getProperty(String key) {
    return PropertiesUtils.get(key);
  }

  /**
   * 从当前配置文件获取配置参数值
   *
   * @param key
   * @param defaultValue 默认值
   * @return
   */
  public static String getProperty(String key, String defaultValue) {
    String value = PropertiesUtils.get(key);
    return value != null ? value : defaultValue;
  }

  /***
   *  从默认的/指定的 Properties文件获取boolean值
   * @param key
   * @return
   */
  public static boolean isTrue(String key) {
    return PropertiesUtils.getBoolean(key);
  }

  /***
   * 获取int类型
   * @param key
   * @return
   */
  public static Integer getInteger(String key) {
    return PropertiesUtils.getInteger(key);
  }

  /***
   * 获取int类型
   * @param key
   * @return
   */
  public static Integer getInteger(String key, int defaultValue) {
    Integer value = PropertiesUtils.getInteger(key);
    return value != null ? value : defaultValue;
  }

  /***
   * 获取截取长度
   * @return
   */
  public static int getCutLength() {
    if (cutLength == null) {
      cutLength = PropertiesUtils.getInteger("diboot.core.cut-length");
      if (cutLength == null) {
        cutLength = 20;
      }
    }
    return cutLength;
  }

  /***
   * 默认页数
   * @return
   */
  public static int getPageSize() {
    if (pageSize == null) {
      pageSize = PropertiesUtils.getInteger("diboot.core.page-size");
      if (pageSize == null) {
        pageSize = 20;
      }
    }
    return pageSize;
  }

  /***
   * 获取批量插入的每批次数量
   * @return
   */
  public static int getBatchSize() {
    if (batchSize == null) {
      batchSize = PropertiesUtils.getInteger("diboot.core.batch-size");
      if (batchSize == null) {
        batchSize = 1000;
      }
    }
    return batchSize;
  }

  /**
   * 获取有效记录的标记值，如 0
   *
   * @return
   */
  public static Object getActiveFlagValue() {
    String activeValue = getProperty("mybatis-plus.global-config.db-config.logic-not-delete-value");
    initActiveFlagValue(activeValue);
    return ACTIVE_FLAG_VALUE;
  }

  public static void setActiveFlagValue(String value) {
    initActiveFlagValue(value);
  }

  private static void initActiveFlagValue(String activeValue) {
    if (ACTIVE_FLAG_VALUE == null) {
      if (V.notEmpty(activeValue)) {
        if (S.equalsIgnoreCase(activeValue, "false")) {
          ACTIVE_FLAG_VALUE = false;
        } else if (S.containsIgnoreCase(activeValue, "true")) {
          ACTIVE_FLAG_VALUE = true;
        } else {
          ACTIVE_FLAG_VALUE = Integer.parseInt(activeValue);
        }
      }
    }
    if (ACTIVE_FLAG_VALUE == null) {
      ACTIVE_FLAG_VALUE = 0;
    }
  }

  /***
   * 获取workerId
   * @return
   */
  public static long getWorkerId() {
    if (workerId == null) {
      workerId = PropertiesUtils.getLong("diboot.id.worker-id");
      if (workerId == null) {
        workerId = 1L;
      }
    }
    return workerId;
  }

  /***
   * 获取DataCenterId
   * @return
   */
  public static long getDataCenterId() {
    if (dataCenterId == null) {
      dataCenterId = PropertiesUtils.getLong("diboot.id.data-center-id");
      if (dataCenterId == null) {
        dataCenterId = 1L;
      }
    }
    return dataCenterId;
  }

}
