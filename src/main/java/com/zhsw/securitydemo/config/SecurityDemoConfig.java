package com.zhsw.securitydemo.config;

import com.zhsw.securitydemo.config.properties.SecurityUrlProperties;
import com.zhsw.securitydemo.entity.SysUser;
import com.zhsw.securitydemo.handler.*;
import com.zhsw.securitydemo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhengliang
 * @Description: 安全配置类
 * @Date: 2019/12/9 16:56
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class SecurityDemoConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RememberMeHandler rememberMeHandler;
    @Autowired
    private SecurityUrlProperties securityUrlProperties;


    /**
     * 页面配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //配置登陆界面
        http.formLogin().loginPage("/login").permitAll();
        //配置登陆成功界面
        //http.formLogin().defaultSuccessUrl("/welcome");
        http.formLogin().successHandler(new LoginSuccessHandler());
        //配置登出授权
        http.logout().permitAll();
        //配置记住我
        http.rememberMe()
                .tokenRepository(rememberMeHandler)
                .tokenValiditySeconds(60*60*24)
                .userDetailsService(userDetailsService());
        //用户权限不足处理
        http.exceptionHandling().accessDeniedHandler(new AuthLimitHandler());
        http.authorizeRequests()
                //所有得静态文件都可访问
                .antMatchers(securityUrlProperties.getPermitAllList().toString()).permitAll()
                .antMatchers(securityUrlProperties.getAnonymousList().toString()).anonymous()
                //动态url权限
                .withObjectPostProcessor(new DefinedObjectPostProcessor())
                //url决策
                .accessDecisionManager(accessDecisionManager())
                .anyRequest().fullyAuthenticated();
    }
    /**
     * 配置用户名验证
     * @return
     */
    @Override
    protected UserDetailsService userDetailsService() {
      return username ->{
          if (username == null || username.trim().length() <=0){
              throw new UsernameNotFoundException("用户名为空");
          }
          SysUser sysUser = sysUserService.selectByUserName(username);
          System.err.println(sysUser);
          if (sysUser !=null){
              return sysUser;
          }
          throw new UsernameNotFoundException("用户名不存在");
      };
    }

    /**
     * 配置用户密码加密
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       // 内存用户密码加密 auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder());
        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }
    /**
     * AffirmativeBased – 任何一个AccessDecisionVoter返回同意则允许访问
     * ConsensusBased – 同意投票多于拒绝投票（忽略弃权回答）则允许访问
     * UnanimousBased – 每个投票者选择弃权或同意则允许访问
     *
     * 决策管理
     */
    private AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<>();
        decisionVoters.add(new WebExpressionVoter());
        decisionVoters.add(new AuthenticatedVoter());
        decisionVoters.add(new RoleVoter());
        /* 路由权限管理 */
        decisionVoters.add(new UrlRoleAuthHandler());
        return new UnanimousBased(decisionVoters);
    }
    /**
     * 自定义处理类
     */
    @Autowired
    private UrlRolesFilterHandler urlRolesFilterHandler;
    class DefinedObjectPostProcessor implements ObjectPostProcessor<FilterSecurityInterceptor> {
        @Override
        public <O extends FilterSecurityInterceptor> O postProcess(O object) {
            object.setSecurityMetadataSource(urlRolesFilterHandler);
            return object;
        }
    }
}
