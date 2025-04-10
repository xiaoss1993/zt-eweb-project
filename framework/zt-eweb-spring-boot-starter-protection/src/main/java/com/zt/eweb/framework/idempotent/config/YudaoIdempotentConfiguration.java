package com.zt.eweb.framework.idempotent.config;

import com.zt.eweb.framework.idempotent.core.aop.IdempotentAspect;
import com.zt.eweb.framework.idempotent.core.keyresolver.impl.DefaultIdempotentKeyResolver;
import com.zt.eweb.framework.idempotent.core.keyresolver.impl.ExpressionIdempotentKeyResolver;
import com.zt.eweb.framework.idempotent.core.keyresolver.IdempotentKeyResolver;
import com.zt.eweb.framework.idempotent.core.keyresolver.impl.UserIdempotentKeyResolver;
import com.zt.eweb.framework.idempotent.core.redis.IdempotentRedisDAO;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import com.zt.eweb.framework.redis.config.YudaoRedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@AutoConfiguration(after = YudaoRedisAutoConfiguration.class)
public class YudaoIdempotentConfiguration {

    @Bean
    public IdempotentAspect idempotentAspect(List<IdempotentKeyResolver> keyResolvers, IdempotentRedisDAO idempotentRedisDAO) {
        return new IdempotentAspect(keyResolvers, idempotentRedisDAO);
    }

    @Bean
    public IdempotentRedisDAO idempotentRedisDAO(StringRedisTemplate stringRedisTemplate) {
        return new IdempotentRedisDAO(stringRedisTemplate);
    }

    // ========== 各种 IdempotentKeyResolver Bean ==========

    @Bean
    public DefaultIdempotentKeyResolver defaultIdempotentKeyResolver() {
        return new DefaultIdempotentKeyResolver();
    }

    @Bean
    public UserIdempotentKeyResolver userIdempotentKeyResolver() {
        return new UserIdempotentKeyResolver();
    }

    @Bean
    public ExpressionIdempotentKeyResolver expressionIdempotentKeyResolver() {
        return new ExpressionIdempotentKeyResolver();
    }

}
