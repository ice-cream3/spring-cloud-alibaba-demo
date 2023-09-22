package com.tx.pay.feign;

import org.springframework.stereotype.Component;

/**
 * @ClassName: PayFeignServiceFallback
 * @Description:
 * @Author: ice
 * @Date: 2023/9/20 11:58
 */
@Component
public class PayFeignServiceFallback implements PayFeignService{

    public String get(String payId) {
        return "降级,降级";
    }

    public String add(String payId) {
        return "降级了,降级了";
    }

    public String error(String payId) {
        return "降级了,降级了";
    }


}
