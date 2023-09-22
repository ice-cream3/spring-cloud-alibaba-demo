package com.tx.pay.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

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
@FeignClient(value = "pay-service", path = "/pay", fallback = PayFeignServiceFallback.class)
// 局部日志配置如下
// @FeignClient(value = "pay-service", path = "/pay", configuration = FeignConfig.class)
public interface PayFeignService {

    // 原生注解:默认契约 @RequestLine == @RequestMapping
    @RequestMapping("/get")
    String get(String payId);

    @RequestMapping("/add")
    String add(String payId);

    @RequestMapping("/error")
    public String error(String payId);
}
