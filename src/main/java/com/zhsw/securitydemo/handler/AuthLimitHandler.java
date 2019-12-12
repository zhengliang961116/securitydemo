package com.zhsw.securitydemo.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: zhengliang
 * @Description: 权限不足处理
 * @Date: 2019/12/10 14:11
 */
public class AuthLimitHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                                    AccessDeniedException e) throws IOException, ServletException {
        System.out.println("您没有权限访问"+httpServletRequest.getRequestURI());
        httpServletResponse.sendRedirect("/error");

    }
}
