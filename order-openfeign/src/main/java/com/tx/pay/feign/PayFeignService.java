package com.tx.pay.feign;

import com.tx.pay.config.FeignConfig;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

/**
 * @ClassName: PayFeignService
 * @Description:
 * @Author: ice
 * @Date: 2023/9/15 15:49
 */

/**
 * value:指定接口服务名
 * path:指定接口类指定的@RequestMapping路径
 */
@FeignClient(value = "pay-service", path = "/pay")
// 局部日志配置如下
// @FeignClient(value = "pay-service", path = "/pay", configuration = FeignConfig.class)
public interface PayFeignService {

    @RequestMapping("/error")
    String error(String payId);

    // 原生注解:默认契约 @RequestLine == @RequestMapping
    @RequestMapping("/get")
    String get(String payId);

    @RequestMapping("/add")
    String add(String payId);
}
