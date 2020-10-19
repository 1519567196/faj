package com.resjz.web.config.security;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocalAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    /**
     * 匿名用户处理  就是没有登录用户
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        request.setAttribute("info","请先登录");
        request.getRequestDispatcher("/login.html").forward(request,response);
//        response.getWriter().print("alert('无注册信息')");
    }
}
