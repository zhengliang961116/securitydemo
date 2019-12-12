package com.zhsw.securitydemo.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Author: zhengliang
 * @Description: 角色实体类
 * @Date: 2019/12/10 14:56
 */
public class SysRole  implements GrantedAuthority {
    private int id;
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public SysRole() {
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
