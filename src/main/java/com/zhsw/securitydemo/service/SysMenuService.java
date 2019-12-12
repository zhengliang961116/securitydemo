package com.zhsw.securitydemo.service;

import com.zhsw.securitydemo.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: zhengliang
 * @Description: 菜单service层
 * @Date: 2019/12/11 10:53
 */
public interface SysMenuService {
    List<String> selectRoleByUrl(String url);
}
