package com.tx.pay.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MyServerProperties
 * @Description:
 * @Author: ice
 * @Date: 2023/10/17 12:37
 */
@Configuration
public class MyServerProperties {

    @Bean(name = "NacosUser")
    @ConfigurationProperties(prefix = "user.likes")
    List<String> list() {
        return new ArrayList<>();
    }
}
