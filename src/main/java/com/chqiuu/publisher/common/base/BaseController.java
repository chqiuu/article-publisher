package com.chqiuu.publisher.common.base;

import com.chqiuu.publisher.common.util.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class BaseController {

    /**
     * 获取当前用户请求对象
     *
     * @return 当前用户请求对象
     */
    public HttpServletRequest getRequest() {
        return SecurityUtils.getHttpServletRequest();
    }

    public String getStringByParameter(String parameter) {
        return SecurityUtils.getHttpServletRequest().getParameter(parameter);
    }

    public Integer getIntByParameter(String parameter) {
        String parameterValue = getStringByParameter(parameter);
        try {
            return Integer.parseInt(parameterValue);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取客户端IP地址
     *
     * @return 客户端IP地址
     */
    public String getClientIp() {
        return SecurityUtils.getClientIp();
    }

    /**
     * 获取当前用户响应对象
     *
     * @return 当前用户响应对象
     */
    public HttpServletResponse getResponse() {
        return SecurityUtils.getResponse();
    }

    /**
     * 获取当前用户Session
     *
     * @return 当前用户Session
     */
    public HttpSession getSession() {
        return SecurityUtils.getSession();
    }
}
