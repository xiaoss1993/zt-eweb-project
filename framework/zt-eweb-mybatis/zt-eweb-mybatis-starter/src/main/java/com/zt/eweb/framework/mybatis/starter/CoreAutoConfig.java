
package com.zt.eweb.framework.mybatis.starter;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.zt.eweb.framework.mybatis.core.converter.Date2LocalDateConverter;
import com.zt.eweb.framework.mybatis.core.converter.Date2LocalDateTimeConverter;
import com.zt.eweb.framework.mybatis.core.converter.LocalDate2DateConverter;
import com.zt.eweb.framework.mybatis.core.converter.LocalDateTime2DateConverter;
import com.zt.eweb.framework.mybatis.core.converter.LocalDateTime2StringConverter;
import com.zt.eweb.framework.mybatis.core.converter.SqlDate2LocalDateConverter;
import com.zt.eweb.framework.mybatis.core.converter.SqlDate2LocalDateTimeConverter;
import com.zt.eweb.framework.mybatis.core.converter.String2BooleanConverter;
import com.zt.eweb.framework.mybatis.core.converter.String2DateConverter;
import com.zt.eweb.framework.mybatis.core.converter.String2ListConverter;
import com.zt.eweb.framework.mybatis.core.converter.String2LocalDateConverter;
import com.zt.eweb.framework.mybatis.core.converter.String2LocalDateTimeConverter;
import com.zt.eweb.framework.mybatis.core.converter.String2MapConverter;
import com.zt.eweb.framework.mybatis.core.converter.Timestamp2LocalDateTimeConverter;
import com.zt.eweb.framework.mybatis.core.data.protect.DataEncryptHandler;
import com.zt.eweb.framework.mybatis.core.data.protect.DataMaskHandler;
import com.zt.eweb.framework.mybatis.core.data.protect.DefaultDataEncryptHandler;
import com.zt.eweb.framework.mybatis.core.data.protect.DefaultDataMaskHandler;
import com.zt.eweb.framework.mybatis.core.interceptor.MpInterceptor;
import com.zt.eweb.framework.mybatis.core.serial.deserializer.LocalDateTimeDeserializer;
import com.zt.eweb.framework.mybatis.core.serial.serializer.BigDecimal2StringSerializer;
import com.zt.eweb.framework.mybatis.core.util.D;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.LocalDateTypeHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Diboot Core自动配置类
 *
 * @author mazc@dibo.ltd
 * @version v2.0
 * @date 2019/08/01
 */
@Order(902)
@EnableAsync
@Configuration
@ComponentScan(basePackages = {"com.zt.eweb.framework.mybatis.core"})
@MapperScan(basePackages = {"com.zt.eweb.framework.mybatis.core.mapper"})
public class CoreAutoConfig implements WebMvcConfigurer {

  private static final Logger log = LoggerFactory.getLogger(CoreAutoConfig.class);

  @Value("${spring.jackson.date-format:" + D.FORMAT_DATETIME_Y4MDHMS + "}")
  private String defaultDatePattern;

  @Value("${spring.jackson.time-zone:GMT+8}")
  private String defaultTimeZone;

  @Value("${spring.jackson.default-property-inclusion:NON_NULL}")
  private JsonInclude.Include defaultPropertyInclusion;

  public CoreAutoConfig() {
    log.info("初始化 core 内核 自动配置");
  }

  /**
   * 默认配置 ObjectMapper, 并允许用户覆盖
   *
   * @return Jackson2ObjectMapperBuilderCustomizer
   */
  @Bean
  @ConditionalOnMissingBean
  public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
    return builder -> {
      // Long转换成String避免JS超长问题
      builder.serializerByType(Long.class, ToStringSerializer.instance);
      builder.serializerByType(Long.TYPE, ToStringSerializer.instance);
      builder.serializerByType(BigInteger.class, ToStringSerializer.instance);
      // BigDecimal转换成String避免JS超长问题，以及格式化数值
      builder.serializerByType(BigDecimal.class, BigDecimal2StringSerializer.instance);

      // 支持java8时间类型
      // LocalDateTime
      DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern(
          D.FORMAT_DATETIME_Y4MDHMS);
      builder.serializerByType(LocalDateTime.class,
          new LocalDateTimeSerializer(localDateTimeFormatter));
      builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer());
      // LocalDate
      DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern(D.FORMAT_DATE_Y4MD);
      builder.serializerByType(LocalDate.class, new LocalDateSerializer(localDateFormatter));
      builder.deserializerByType(LocalDate.class, new LocalDateDeserializer(localDateFormatter));
      // LocalTime
      DateTimeFormatter localTimeFormatter = DateTimeFormatter.ofPattern(D.FORMAT_TIME_HHmmss);
      builder.serializerByType(LocalTime.class, new LocalTimeSerializer(localTimeFormatter));
      builder.deserializerByType(LocalTime.class, new LocalTimeDeserializer(localTimeFormatter));

      // 设置序列化包含策略
      builder.serializationInclusion(defaultPropertyInclusion);
      // 时间格式化
      builder.failOnUnknownProperties(false);
      builder.timeZone(TimeZone.getTimeZone(defaultTimeZone));
      SimpleDateFormat dateFormat = new SimpleDateFormat(defaultDatePattern) {
        @Override
        public Date parse(String dateStr) {
          return D.fuzzyConvert(dateStr);
        }
      };
      builder.dateFormat(dateFormat);
    };
  }

  @Bean
  @ConditionalOnMissingBean
  public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
    Jackson2ObjectMapperBuilder objectMapperBuilder = new Jackson2ObjectMapperBuilder();
    jsonCustomizer().customize(objectMapperBuilder);
    log.info("启用diboot默认的Jackson自定义配置");
    return objectMapperBuilder;
  }

  @Bean
  @ConditionalOnMissingBean
  public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
    return new MappingJackson2HttpMessageConverter(jackson2ObjectMapperBuilder().build());
  }

  /**
   * 使用多个功能需要注意顺序关系,建议使用如下顺序 多租户,动态表名 分页,乐观锁 sql性能规范,防止全表更新与删除 总结:
   * 对sql进行单次改造的优先放入,不对sql进行改造的最后放入
   */
  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor(List<MpInterceptor> interceptors) {
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    interceptors.stream()
        .sorted(Comparator.comparing(MpInterceptor::getSortNo))
        .map(MpInterceptor::getInnerInterceptor)
        .forEach(interceptor::addInnerInterceptor);

    return interceptor;
  }

  /**
   * 数据加密解密处理器
   */
  @Bean
  @ConditionalOnMissingBean(DataEncryptHandler.class)
  public DataEncryptHandler dataEncryptHandler() {
    log.debug("初始化默认的DataEncryptHandler");
    return new DefaultDataEncryptHandler();
  }

  /**
   * 数据脱敏处理器
   */
  @Bean
  @ConditionalOnMissingBean(DataMaskHandler.class)
  public DataMaskHandler dataMaskHandler() {
    log.debug("初始化默认的DataMaskHandler");
    return new DefaultDataMaskHandler();
  }

  /**
   * 默认支持String-Date类型转换
   *
   * @param registry registry
   */
  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(new Date2LocalDateConverter());
    registry.addConverter(new Date2LocalDateTimeConverter());
    registry.addConverter(new LocalDate2DateConverter());
    registry.addConverter(new LocalDateTime2DateConverter());
    registry.addConverter(new LocalDateTime2StringConverter());
    registry.addConverter(new SqlDate2LocalDateConverter());
    registry.addConverter(new SqlDate2LocalDateTimeConverter());
    registry.addConverter(new String2DateConverter());
    registry.addConverter(new String2LocalDateConverter());
    registry.addConverter(new String2LocalDateTimeConverter());
    registry.addConverter(new String2BooleanConverter());
    registry.addConverter(new String2ListConverter());
    registry.addConverter(new String2MapConverter());
    registry.addConverter(new Timestamp2LocalDateTimeConverter());
  }

  /**
   * 扩展Mybatis 类型转换，支持日期类型转为LocalDate等
   */
  @Bean
  @ConditionalOnMissingBean
  public ConfigurationCustomizer typeHandlerRegistry() {
    return configuration -> configuration.getTypeHandlerRegistry()
        .register(java.sql.Date.class, JdbcType.DATE, LocalDateTypeHandler.class);
  }

}
