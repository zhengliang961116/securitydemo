package com.zhsw.securitydemo;

import com.zhsw.securitydemo.config.properties.SecurityUrlProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityDemoApplicationTests {
    @Autowired
    private SecurityUrlProperties  securityUrlProperties ;
    @Test
    public void fun1(){
        System.out.println(securityUrlProperties.getPermitAllList());
        System.out.println(securityUrlProperties.getAnonymousList());
    }

}
