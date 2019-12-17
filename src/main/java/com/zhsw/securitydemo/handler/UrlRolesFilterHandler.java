package com.zhsw.securitydemo.handler;

import com.zhsw.securitydemo.service.SysMenuService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @Author: zhengliang
 * @Description: 路由动态获取角色
 * @Date: 2019/12/11 11:11
 */
@Component
public class UrlRolesFilterHandler implements FilterInvocationSecurityMetadataSource {
    @Resource
    private SysMenuService sysMenuService;
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<String> roleNames = sysMenuService.selectRoleByUrl(requestUrl);
        String[] names = new String[roleNames.size()];
        for (int i = 0; i < roleNames.size(); i++) {
            names[i] = roleNames.get(i);
        }
        return SecurityConfig.createList(names);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return  FilterInvocation.class.isAssignableFrom(clazz);
    }
}
