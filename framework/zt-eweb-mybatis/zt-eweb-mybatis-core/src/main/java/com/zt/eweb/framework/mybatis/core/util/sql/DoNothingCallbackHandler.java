package com.zt.eweb.framework.mybatis.core.util.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowCallbackHandler;

/**
 * 不需要回调的处理器
 */
public class DoNothingCallbackHandler implements RowCallbackHandler {

  @Override
  public void processRow(ResultSet rs) throws SQLException {
  }
}
