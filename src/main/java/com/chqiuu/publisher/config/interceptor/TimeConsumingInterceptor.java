package com.chqiuu.publisher.config.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.extra.servlet.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 拦截器，统计接口耗时
 *
 * @author chqiuu
 */
@Slf4j
public class TimeConsumingInterceptor implements AsyncHandlerInterceptor {

    private final static int SLOW_MIN_TIME_MILLIS = 3000;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.getSession();
        // 记录请求开始时间
        request.setAttribute("_startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        // 请求结束时间
        Long endTime = System.currentTimeMillis();
        // 从HttpServletRequest获取开始时间
        Long startTime = (Long) request.getAttribute("_startTime");
        int elapsedTimeMillis = Math.toIntExact(endTime - startTime);
        // 打印接口信息及耗时
        if (elapsedTimeMillis > SLOW_MIN_TIME_MILLIS) {
            log.warn("SessionId: {} {} {}；耗时：{}s", request.getSession().getId(), JakartaServletUtil.getClientIP(request, ""), getFullUrl(request), elapsedTimeMillis / 1000);
        } else {
            log.info("SessionId: {} {} {}；耗时：{}s", request.getSession().getId(), JakartaServletUtil.getClientIP(request, ""), getFullUrl(request), elapsedTimeMillis / 1000);
        }
    }

    /**
     * 获取完整的URL路径
     *
     * @param request 请求对象{@link HttpServletRequest}
     * @return 完整的URL路径
     */
    private String getFullUrl(HttpServletRequest request) {
        //记录请求参数
        StringBuilder sb = new StringBuilder();
        String method = request.getMethod();
        sb.append(method).append(" ");
        sb.append(request.getRequestURL().toString());
        if (RequestMethod.POST.name().equals(method)) {
            //获取参数
            Map<String, String[]> pm = request.getParameterMap();
            Set<Map.Entry<String, String[]>> es = pm.entrySet();
            Iterator<Map.Entry<String, String[]>> iterator = es.iterator();
            int pointer = 0;
            while (iterator.hasNext()) {
                if (pointer == 0) {
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                Map.Entry<String, String[]> next = iterator.next();
                String key = next.getKey();
                String[] value = next.getValue();
                for (int i = 0; i < value.length; i++) {
                    if (i != 0) {
                        sb.append("&");
                    }
                    if (value[i].length() <= 20) {
                        sb.append(key).append("=").append(value[i]);
                    } else {
                        sb.append(key).append("=").append(StrUtil.subPre(value[i], 20)).append("…");
                    }
                }
                pointer++;
            }
        }
        return sb.toString();
    }
}