package com.zhsw.securitydemo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

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
}
