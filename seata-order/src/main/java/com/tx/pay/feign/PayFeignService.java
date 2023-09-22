package com.tx.pay.feign;

import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "seata-pay", path = "/pay")
public interface PayFeignService {

    /**
     * 当rpc调用有多个参数时，正确写法参数应该加上@RequestParam(“xxx”)，指定请求类型get、post
     */
    @RequestMapping("/create")
    String add(@RequestParam("name") String name, @RequestParam("amount") BigDecimal amount);
}
