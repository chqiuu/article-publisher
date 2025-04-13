package com.chqiuu.publisher.common.util;

import cn.hutool.extra.servlet.JakartaServletUtil;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 获取当前请求（线程）对象信息工具类
 *
 * @author chqiuu
 */
public class SecurityUtils {

    /**
     * 获取当前线程的 ServletRequestAttributes
     *
     * @return ServletRequestAttributes对象
     */
    public static ServletRequestAttributes getServletRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    /**
     * 获取当前用户（线程）请求对象
     *
     * @return 当前用户（线程）请求对象
     */
    public static HttpServletRequest getHttpServletRequest() {
        return getServletRequestAttributes().getRequest();
    }

    /**
     * 获取客户端IP地址
     *
     * @return 客户端IP地址
     */
    public static String getClientIp() {
        return JakartaServletUtil.getClientIP(getHttpServletRequest(), "");
    }

    /**
     * 获取当前用户响应对象
     *
     * @return 当前用户响应对象
     */
    public static HttpServletResponse getResponse() {
        return getServletRequestAttributes().getResponse();
    }

    /**
     * 获取当前用户Session对象
     *
     * @return 当前请求Session对象
     */
    public static HttpSession getSession() {
        return getHttpServletRequest().getSession();
    }

    /**
     * 获取ServletContext对象
     *
     * @return ServletContext对象
     */
    public static ServletContext getServletContext() {
        return getHttpServletRequest().getServletContext();
    }
}
