package com.magic80.springbootcommon.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

public class CorsInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            response.setStatus(HttpServletResponse.SC_OK);
            return false;
        }

        String Origin = request.getHeader("Origin");
        if(null != Origin){
            response.setHeader("Access-Control-Allow-Origin", Origin);
        }
//        StringBuffer sb = new StringBuffer();
        String headerString = String.join(",", Collections.list(request.getHeaderNames()));
//        Enumeration<String> headerNames =  request.getHeaderNames();
//        while (headerNames.hasMoreElements()){
//            sb.append(headerNames.nextElement() + ",");
//        }
//        String headerStr = sb.substring(0,sb.lastIndexOf(","));
        response.setHeader("Access-Control-Allow-Methods", method);
        response.setHeader("Access-Control-Allow-Headers", headerString);
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Control-Allow-Origin,token");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        return super.preHandle(request, response, handler);
    }
}
