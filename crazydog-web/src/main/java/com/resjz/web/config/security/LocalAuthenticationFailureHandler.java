package com.resjz.web.config.security;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocalAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        request.setAttribute("info","用户名或密码错误");
        request.getRequestDispatcher("/login.html").forward(request,response);
    }
}
