package com.zhsw.securitydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @Author: zhengliang
 * @Description: 登陆控制类
 * @Date: 2019/12/9 16:50
 */
@Controller
public class loginController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/welcome")
    public String  welcome(){
        return  "welcome";
    }
}
