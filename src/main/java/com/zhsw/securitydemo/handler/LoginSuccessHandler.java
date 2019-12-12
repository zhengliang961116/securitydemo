package com.zhsw.securitydemo.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: zhengliang
 * @Description: 登陆成功处理
 * @Date: 2019/12/10 9:19
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

   @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
       System.out.println("******************登陆成功");
       httpServletResponse.sendRedirect("/welcome");
    }
}
