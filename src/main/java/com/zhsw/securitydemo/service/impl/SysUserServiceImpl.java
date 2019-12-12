package com.zhsw.securitydemo.service.impl;

import com.zhsw.securitydemo.entity.SysUser;
import com.zhsw.securitydemo.mapper.SysUserMapper;
import com.zhsw.securitydemo.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: zhengliang
 * @Description: 用户service实现类
 * @Date: 2019/12/10 15:05
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;
    @Override
    public SysUser selectByUserName(String username) {
        return sysUserMapper.selectByUserName(username);
    }
}
