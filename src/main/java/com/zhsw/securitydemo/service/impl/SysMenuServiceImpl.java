package com.zhsw.securitydemo.service.impl;


import com.zhsw.securitydemo.mapper.SysMenuMapper;
import com.zhsw.securitydemo.service.SysMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: zhengliang
 * @Description: 菜单service实现类
 * @Date: 2019/12/11 10:53
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Resource
    private SysMenuMapper sysMenuMapper;
    @Override
    public List<String> selectRoleByUrl(String url) {
        return sysMenuMapper.selectRoleByUrl(url);
    }
}
