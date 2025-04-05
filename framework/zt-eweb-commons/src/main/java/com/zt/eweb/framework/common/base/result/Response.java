package com.zt.eweb.framework.common.base.result;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.DependsOn;

/**
 * @author longli
 */
@Data
@AllArgsConstructor
@DependsOn
public class Response<T> {

    /**
     * 响应码，非0 即为异常
     */
    private final String code;
    /**
     * 响应消息
     */
    private final String msg;
    /**
     * 响应数据
     */
    private final T data;
    private final String traceId;

    /**
     * 请求id
     */
    private final Boolean success;

    protected Response(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.success = StrUtil.equals("0", code);
        this.traceId = IdUtil.getSnowflakeNextIdStr();
        //EasyTraceUtil.getTraceId();
    }

    public static <T> Response<T> ok(T data) {
        return new Response<>("0", "操作成功", data);
    }

    public static <Void> Response<Void> ok() {
        return new Response<>("0", "操作成功", null);
    }

    public static <T> Response<T> error(T data) {
        return new Response<>("400", "", data);
    }

    public static <T> Response<T> error(String code, String msg, T data) {
        return new Response<>(code, msg, data);
    }

    public static <T> Response<T> error(String code, String msg) {
        return new Response<>(code, msg, null);
    }

    public static <T> Response<T> error400(String msg) {
        return new Response<>("400", msg, null);
    }

    public static <T> Response<T> error401(String msg) {
        return new Response<>("401", msg, null);
    }

    public static <T> Response<T> error403(String msg) {
        return new Response<>("403", msg, null);
    }

    public static <T> Response<T> error500(String msg) {
        return new Response<>("500", msg, null);
    }
}
