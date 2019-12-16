package com.zhsw.securitydemo.service;

import com.zhsw.securitydemo.entity.SysUser;

import java.util.List;

/**
 * @Author: zhengliang
 * @Description: 用户service接口
 * @Date: 2019/12/10 15:04
 */
public interface SysUserService {
    SysUser selectByUserName(String username);
    List<String> selectNoAuthUrl();
}
