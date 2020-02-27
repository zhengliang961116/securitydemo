package com.zhsw.securitydemo.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengLiang
 * @description
 * @date 2020/2/26 16:00
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "security1")
public class SecurityUrlProperties {
    private List<String> permitAllList = new ArrayList<>();
    private List<String> anonymousList = new ArrayList<>();
}
