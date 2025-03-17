package com.zt.eweb.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DataChangeRecorderInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.IllegalSQLInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.p6spy.engine.event.JdbcEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 模块名 :
 * 文件名 :
 * 创建时间 : 2025/3/16 11:51
 * 实现功能 :
 * <p>
 * 作者 : xiaoss
 * 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ----------------------------------------------------------------
 * 修改记录
 * 日 期     	版本     修改人  修改内容
 * 2025/3/16      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@Configuration
public class MybatisPlusConfig {
    @Autowired(required = false)
    private List<JdbcEventListener> listeners;
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        BlockAttackInnerInterceptor blockAttackInnerInterceptor = new BlockAttackInnerInterceptor();
        IllegalSQLInnerInterceptor  illegalSQLInnerInterceptor  = new IllegalSQLInnerInterceptor();
        PaginationInnerInterceptor  paginationInnerInterceptor  = new PaginationInnerInterceptor();
        DataChangeRecorderInnerInterceptor  dataChangeRecorderInnerInterceptor = new DataChangeRecorderInnerInterceptor();
        interceptor.addInnerInterceptor(blockAttackInnerInterceptor);
        interceptor.addInnerInterceptor(illegalSQLInnerInterceptor);
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        interceptor.addInnerInterceptor(dataChangeRecorderInnerInterceptor);
        return interceptor;
    }

}
