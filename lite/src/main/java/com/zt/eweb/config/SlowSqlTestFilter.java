package com.zt.eweb.config;

import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.filter.stat.StatFilterMBean;
import com.alibaba.druid.proxy.jdbc.DataSourceProxy;
import com.alibaba.druid.proxy.jdbc.JdbcParameter;
import com.alibaba.druid.proxy.jdbc.ResultSetProxy;
import com.alibaba.druid.proxy.jdbc.StatementProxy;
import com.alibaba.druid.support.json.JSONWriter;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.NClob;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 模块名 : 文件名 : 创建时间 : 2025/3/17 12:42 实现功能 :
 * <p>
 * 作者 : xiaoss 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ---------------------------------------------------------------- 修改记录 日 期     	版本     修改人
 * 修改内容 2025/3/17      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@Component
public class SlowSqlTestFilter extends FilterEventAdapter implements StatFilterMBean {

  private static final String IGNORE_SQL = "SELECT 1";

  private static final String INSERT = "insert";
  private static final String UPDATE = "update";
  private static final String DELETE = "delete";

  @Override
  public void init(DataSourceProxy dataSource) {
    super.init(dataSource);
  }

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private HttpServletRequest request;

  @Override
  protected void statementExecuteBefore(StatementProxy statement, String sql) {
    super.statementExecuteBefore(statement, sql);
    //sql开始执行的时间
    statement.setLastExecuteStartNano();
  }

  @Override
  public boolean statement_execute(FilterChain chain, StatementProxy statement, String sql) throws SQLException {
    return super.statement_execute(chain, statement, sql);
  }

  @Override
  protected void statementExecuteBatchBefore(StatementProxy statement) {
    super.statementExecuteBatchBefore(statement);
    //sql开始执行的时间
    statement.setLastExecuteTimeNano();
  }

  @Override
  protected void statementExecuteAfter(StatementProxy statement, String sql, boolean result) {
    if (IGNORE_SQL.equals(sql)) {
      return;
    }
    if (sql.contains(INSERT)) {
      System.out.println("SQL statementExecuteAfter INSERT");
    }
    if (sql.contains(UPDATE)) {
      System.out.println("SQL statementExecuteAfter UPDATE");
    }
//        if (slowSqlConfig == null) {
//            slowSqlConfig = SpringUtil.getBean(SlowSqlConfig.class);
//        }
    final long nonNano = System.nanoTime();
    final long lastTime = nonNano - statement.getLastExecuteStartNano();
    long millis = lastTime / (1000 * 1000);
//        if (millis >= slowSqlConfig.getSlowSqlMillis()) {
//            slowSqlToMysql(statement, sql,millis);
//        }

  }


  @Override
  protected void statementExecuteUpdateBefore(StatementProxy statement, String sql) {
    super.statementExecuteUpdateBefore(statement, sql);
    statement.setLastExecuteTimeNano();
  }

  @Override
  protected void statementExecuteUpdateAfter(StatementProxy statement, String sql,
      int updateCount) {
    final long nonNano = System.nanoTime();
    final long lastTime = nonNano - statement.getLastExecuteStartNano();
    long millis = lastTime / (1000 * 1000);
//        if (millis >= slowSqlConfig.getSlowSqlMillis()) {
//            slowSqlToMysql(statement, sql,millis);
//        }
  }

  @Override
  protected void statementExecuteQueryBefore(StatementProxy statement, String sql) {
    super.statementExecuteQueryBefore(statement, sql);
    statement.setLastExecuteStartNano();
  }

  @Override
  protected void statementExecuteQueryAfter(StatementProxy statement, String sql,
      ResultSetProxy resultSet) {
    if (IGNORE_SQL.equals(sql)) {
      return;
    }
    final long nonNano = System.nanoTime();
    final long lastTime = nonNano - statement.getLastExecuteStartNano();
    long millis = lastTime / (1000 * 1000);
//        if (millis >= slowSqlConfig.getSlowSqlMillis()) {
//            slowSqlToMysql(statement, sql,millis);
//        }
  }


  @Override
  protected void statement_executeErrorAfter(StatementProxy statement, String sql,
      Throwable error) {
    super.statement_executeErrorAfter(statement, sql, error);
  }

  private void slowSqlToMysql(StatementProxy statement, String sql, Long millis) {
//        if (bbsDruidSqlDao == null) {
//            bbsDruidSqlDao = SpringUtil.getBean(BbsDruidSqlDao.class);
//        }
    String slowParamters = buildSlowParameters(statement);
    logger.info("慢sql语句{},入参:{}", sql, slowParamters);
//        BbsDruidSql bbsDruidSql = BbsDruidSql.builder().slowSql(sql).slowSqlParam(slowParamters)
//            .createTime(new Date()).updateTime(new Date()).slowSqlTraceid(millis.toString()).build();
//        bbsDruidSqlDao.insertSelective(bbsDruidSql);
  }

  protected String buildSlowParameters(StatementProxy statement) {
    JSONWriter out = new JSONWriter();

    out.writeArrayStart();
    for (int i = 0, parametersSize = statement.getParametersSize(); i < parametersSize; ++i) {
      JdbcParameter parameter = statement.getParameter(i);
      if (i != 0) {
        out.writeComma();
      }
      if (parameter == null) {
        continue;
      }

      Object value = parameter.getValue();
      if (value == null) {
        out.writeNull();
      } else if (value instanceof String) {
        String text = (String) value;
        if (text.length() > 100) {
          out.writeString(text.substring(0, 97) + "...");
        } else {
          out.writeString(text);
        }
      } else if (value instanceof Number) {
        out.writeObject(value);
      } else if (value instanceof java.util.Date) {
        out.writeObject(value);
      } else if (value instanceof Boolean) {
        out.writeObject(value);
      } else if (value instanceof InputStream) {
        out.writeString("<InputStream>");
      } else if (value instanceof NClob) {
        out.writeString("<NClob>");
      } else if (value instanceof Clob) {
        out.writeString("<Clob>");
      } else if (value instanceof Blob) {
        out.writeString("<Blob>");
      } else {
        out.writeString('<' + value.getClass().getName() + '>');
      }
    }
    out.writeArrayEnd();

    return out.toString();
  }

  @Override
  public boolean isMergeSql() {
    return false;
  }

  @Override
  public void setMergeSql(boolean mergeSql) {

  }

  @Override
  public boolean isLogSlowSql() {
    return false;
  }

  @Override
  public void setLogSlowSql(boolean logSlowSql) {

  }

  @Override
  public String mergeSql(String sql, String dbType) {
    return null;
  }

  @Override
  public long getSlowSqlMillis() {
    return 0;
  }

  @Override
  public void setSlowSqlMillis(long slowSqlMillis) {

  }

}