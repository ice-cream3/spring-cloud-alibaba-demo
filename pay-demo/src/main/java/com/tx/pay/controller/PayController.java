package com.tx.pay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/get")
    public String get(String payId) {
        String orderNo = restTemplate.getForObject("http://127.0.0.1:8081/order/get", String.class);
        logger.info("订单服务查询订单号:{}", orderNo);
        return "查询支付:"+payId+",订单号:"+ orderNo;
    }

    @RequestMapping("/add")
    public String add(String payId) {
        String orderNo = restTemplate.getForObject("http://127.0.0.1:8081/order/add", String.class);
        logger.info("订单服务创建订单号:{}", orderNo);
        return "创建支付:"+payId+",订单号:"+ orderNo;
    }
}
