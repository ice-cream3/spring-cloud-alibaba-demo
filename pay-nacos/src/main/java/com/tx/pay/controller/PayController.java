package com.tx.pay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * @ClassName: PayController
 * @Description:
 * @Author: ice
 * @Date: 2023/9/15 11:25
 */
@RestController
@RequestMapping("/pay")
public class PayController {

    private static final Logger logger = LoggerFactory.getLogger(PayController.class);

    @Value("${server.port}")
    String post;

    @RequestMapping("/error")
    public String error(String payId) {
        logger.info("查询支付,id:{},post:{}", payId, post);
        int a = 1/0;
        return post + ":支付id:"+payId+",支付流水:"+ UUID.randomUUID();
    }

    @RequestMapping("/get")
    public String get(String payId) {
        logger.info("查询支付,id:{},post:{}", payId, post);
        return post + ":支付id:"+payId+",支付流水:"+ UUID.randomUUID();
    }

    @RequestMapping("/add")
    public String add(String payId) throws InterruptedException {
        // Thread.sleep(3000);
        logger.info("创建支付,id:{},post:{}", payId, post);
        return post + ":创建支付:"+payId+",支付流水:"+ UUID.randomUUID();
    }
}
