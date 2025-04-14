package com.zt.eweb.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.zt.eweb.framework.common.config.EasyConfig;
import com.zt.eweb.framework.mybatis.core.converter.Date2LocalDateConverter;
import com.zt.eweb.framework.mybatis.core.converter.Date2LocalDateTimeConverter;
import com.zt.eweb.framework.mybatis.core.converter.LocalDate2DateConverter;
import com.zt.eweb.framework.mybatis.core.converter.LocalDateTime2DateConverter;
import com.zt.eweb.framework.mybatis.core.converter.LocalDateTime2StringConverter;
import com.zt.eweb.framework.mybatis.core.converter.SqlDate2LocalDateConverter;
import com.zt.eweb.framework.mybatis.core.converter.SqlDate2LocalDateTimeConverter;
import com.zt.eweb.framework.mybatis.core.converter.String2BooleanConverter;
import com.zt.eweb.framework.mybatis.core.converter.String2DateConverter;
import com.zt.eweb.framework.mybatis.core.converter.String2LocalDateConverter;
import com.zt.eweb.framework.mybatis.core.converter.String2LocalDateTimeConverter;
import com.zt.eweb.framework.mybatis.core.converter.String2MapConverter;
import com.zt.eweb.framework.mybatis.core.converter.Timestamp2LocalDateTimeConverter;
import com.zt.eweb.framework.mybatis.core.serial.deserializer.LocalDateTimeDeserializer;
import com.zt.eweb.framework.mybatis.core.serial.serializer.BigDecimal2StringSerializer;
import com.zt.eweb.framework.mybatis.core.util.D;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 模块名 : 文件名 : 创建时间 : 2025/4/1 16:13 实现功能 :
 * <p>
 * 作者 : xiaoss 版本 : V1.0.0-SNAPSHOT
 *
 * @see
 * @since ---------------------------------------------------------------- 修改记录 日 期     	版本     修改人
 * 修改内容 2025/4/1      V1.0.0  xiaoss   创建
 * ----------------------------------------------------------------
 */
@Configuration
@Slf4j
public class SpringMvcConfig implements WebMvcConfigurer {

    @Value("${spring.jackson.date-format:" + D.FORMAT_DATETIME_Y4MDHMS + "}")
    private String defaultDatePattern;

    @Value("${spring.jackson.time-zone:GMT+8}")
    private String defaultTimeZone;


    @Value("${spring.jackson.default-property-inclusion:NON_NULL}")
    private JsonInclude.Include defaultPropertyInclusion;
    @Resource
    EasyConfig easyConfig;


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
            DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern(D.FORMAT_DATETIME_Y4MDHMS);
            builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(localDateTimeFormatter));
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
        registry.addConverter(new String2MapConverter());
        registry.addConverter(new Timestamp2LocalDateTimeConverter());
    }

    /**
     * 默认首页的设置，当输入域名是可以自动跳转到默认指定的网页
     */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        // 预先配置响应状态代码或视图以呈现响应主体。
//        // 这在不需要自定义控制器逻辑的情况下很有用
//        // @RequestMapping("/html1")
//        //    public String html1(){
//        //        return "index";
//        //    }
//        // ——例如呈现主页、执行简单的站点 URL 重定向、返回带有 HTML 内容的 404 状态、没有内容的 204 等等。
////        registry.addViewController("/").setViewName("login");
////        registry.addViewController("/login.html").setViewName("login");
//    }

//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        // 内容协商
//        // 设置默认的设置默认的ContentType
//        configurer.defaultContentType(MediaType.APPLICATION_JSON);
//    }

}
