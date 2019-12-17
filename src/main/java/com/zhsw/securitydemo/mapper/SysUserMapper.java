package com.zhsw.securitydemo.mapper;

import com.zhsw.securitydemo.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: zhengliang
 * @Description: 用户dao接口
 * @Date: 2019/12/10 15:06
 */
@Mapper
public interface SysUserMapper {
    SysUser selectByUserName(@Param("username") String username);

}
