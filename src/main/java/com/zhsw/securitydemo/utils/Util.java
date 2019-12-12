package com.zhsw.securitydemo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: zhengliang
 * @Description: 工具类
 * @Date: 2019/12/10 16:33
 */
public class Util {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String s= encoder.encode("123456");
        System.out.println(s);
    }
}
