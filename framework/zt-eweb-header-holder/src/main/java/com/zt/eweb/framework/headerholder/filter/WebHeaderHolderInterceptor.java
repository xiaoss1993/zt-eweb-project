package com.zt.eweb.framework.headerholder.filter;

import com.zt.eweb.framework.common.code.ServletCode;
import com.zt.eweb.framework.headerholder.local.HolderContextHolder;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 请求头保存
 *
 */
@RequiredArgsConstructor
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class WebHeaderHolderInterceptor extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            // 保存一些Servlet请求的数据
            HolderContextHolder.put(ServletCode.METHOD, request.getMethod());
            HolderContextHolder.put(ServletCode.CONTEXT_PATH, request.getContextPath());
            HolderContextHolder.put(ServletCode.REQUEST_URI, request.getRequestURI());
            HolderContextHolder.put(ServletCode.REQUEST_URL, request.getRequestURL().toString());
            // 保存请求头数据
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String header = headerNames.nextElement();
                HolderContextHolder.put(header, request.getHeader(header));
            }
            chain.doFilter(request, response);
        }
        finally {
            HolderContextHolder.clear();
        }
    }

}
