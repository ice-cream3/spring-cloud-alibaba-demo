package com.tx.pay.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: RestTemplate
 * @Description:
 * @Author: ice
 * @Date: 2023/9/15 11:30
 */
@Configuration
public class TemplateConfig {

    @Bean
    // 因为注册到nacos时,nacos不会帮我们进行负载,所以需要加LoadBalanced
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
