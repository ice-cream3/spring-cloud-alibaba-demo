package com.tx.pay.config;

import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: FeignConfig
 * @Description:configuration配置是全局日志配置,
 * 如果想要局部日志输出:
 *  1.把@Configuration注解去掉
 *  2.@FeignClient(value = "pay-service", path = "/pay", configuration = FeignConfig.class)
 *  3.也可以不在@FeignClient中配置,在yml配置文件中设置
 *
 * @Author: ice
 * @Date: 2023/9/15 16:06
 */
@Configuration
public class FeignConfig {

    /**
     * feign日志级别管理
     * @return 日志
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 全局超时时间配置
     * @return

    @Bean
    public Request.Options options() {
        return new Request.Options(2000, 3000);
    }*/

    /**
     * 自定义拦截

    @Bean
    public FeignAuthRequestInterceptor feignAuthRequestInterceptor() {
        return new FeignAuthRequestInterceptor();
    }*/
}
