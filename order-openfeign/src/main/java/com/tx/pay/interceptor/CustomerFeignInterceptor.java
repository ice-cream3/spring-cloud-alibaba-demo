package com.tx.pay.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: CustomerFeignInterceptor
 * @Description:
 * @Author: ice
 * @Date: 2023/9/15 16:26
 */
public class CustomerFeignInterceptor implements RequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(CustomerFeignInterceptor.class);

    /**
     * 拦截,参数,头信息等
     * @param requestTemplate
     */
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("name", "");
        requestTemplate.query("payId", "111111");
        requestTemplate.uri("/pay");

        logger.info("自定义拦截");
    }
}
