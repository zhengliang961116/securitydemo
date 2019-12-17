package com.zhsw.securitydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @Author: zhengliang
 * @Description: 方法权限
 * @Date: 2019/12/10 14:03
 */
@RestController
public class AuthController {

    @GetMapping("/one")
    public String  one(){
        return "one";
    }

    @GetMapping("/two")
    public String  two(){
        return "two";
    }

    @GetMapping("/three")
    public String  three(){
        return "three";
    }
    @GetMapping("/css")
    public String  css(){
        return "css";
    }
    @GetMapping("/image")
    public String  image(){
        return "image";
    }
    @GetMapping("/js")
    public String  js(){
        return "js";
    }
    @GetMapping("/common")
    public String  common(){
        return "common";
    }

}
