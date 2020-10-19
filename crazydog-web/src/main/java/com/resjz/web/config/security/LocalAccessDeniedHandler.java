package com.resjz.web.config.security;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocalAccessDeniedHandler implements AccessDeniedHandler {
    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        request.setAttribute("info","对不起，您没有权限");
        request.getRequestDispatcher("/login.html").forward(request,response);//        response.getWriter().print(gson.toJson(new Result<>(ResultStatus.WRONG_DEAL)));
    }
}
