package com.zhsw.securitydemo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: zhengliang
 * @Description: 菜单dao层接口
 * @Date: 2019/12/11 10:51
 */
@Mapper
public interface SysMenuMapper {
    List<String> selectRoleByUrl(@Param("url") String url);
}
