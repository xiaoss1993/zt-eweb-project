package com.zt.eweb.framework.mybatis.core.interceptor;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 插件容器
 *
 */
@Configuration
public class MpInterceptorConfiguration {

    /**
     * 分页
     */
    @Bean
    public MpInterceptor paginationInnerInterceptor() {
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        paginationInnerInterceptor.setOptimizeJoin(false);
        return new MpInterceptor(paginationInnerInterceptor, 1);
    }

    /**
     * 乐观锁
     */
    @Bean
    public MpInterceptor optimisticLockerInnerInterceptor() {
        return new MpInterceptor(new OptimisticLockerInnerInterceptor(), 1);
    }

    /**
     * 防止全表更新与删除
     */
    @Bean
    public MpInterceptor blockAttackInnerInterceptor() {
        return new MpInterceptor(new BlockAttackInnerInterceptor(), 2);
    }

}
