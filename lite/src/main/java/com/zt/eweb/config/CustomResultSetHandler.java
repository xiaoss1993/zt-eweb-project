package com.zt.eweb.config;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;

/**
 * 模块名 : 文件名 : 创建时间 : 2025/4/1 16:39 实现功能 :
 * <p>
 * 作者 : xiaoss 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ---------------------------------------------------------------- 修改记录 日 期     	版本     修改人
 * 修改内容 2025/4/1      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
public class CustomResultSetHandler implements ResultSetHandler {


  @Override
  public <E> List<E> handleResultSets(Statement stmt) throws SQLException {
    return null;
  }

  @Override
  public <E> Cursor<E> handleCursorResultSets(Statement stmt) throws SQLException {
    return null;
  }

  @Override
  public void handleOutputParameters(CallableStatement cs) throws SQLException {

  }
}