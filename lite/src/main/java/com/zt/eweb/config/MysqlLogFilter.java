package com.zt.eweb.config;

import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.ResultSetProxy;
import com.alibaba.druid.proxy.jdbc.StatementProxy;
import org.springframework.stereotype.Component;

/**
 * 模块名 :
 * 文件名 :
 * 创建时间 : 2025/3/17 12:42
 * 实现功能 :
 * <p>
 * 作者 : xiaoss
 * 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ----------------------------------------------------------------
 * 修改记录
 * 日 期     	版本     修改人  修改内容
 * 2025/3/17      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@Component
public class MysqlLogFilter extends FilterEventAdapter {


    protected void statementExecuteUpdateBefore(StatementProxy statement, String sql) {
    }

    protected void statementExecuteUpdateAfter(StatementProxy statement, String sql, int updateCount) {
        showLog(sql);
    }

    protected void statementExecuteQueryBefore(StatementProxy statement, String sql) {
        showLog("statementExecuteQueryBefore " +sql);
    }

    protected void statementExecuteQueryAfter(StatementProxy statement, String sql, ResultSetProxy resultSet) {
        showLog("statementExecuteQueryAfter " +sql);

    }

    protected void statementExecuteBefore(StatementProxy statement, String sql) {
        showLog("statementExecuteBefore " +sql);

    }

    protected void statementExecuteAfter(StatementProxy statement, String sql, boolean result) {
        showLog("statementExecuteAfter " +sql);

    }

    public void showLog(String sql){
        System.out.println("测试"+sql);
    }
}