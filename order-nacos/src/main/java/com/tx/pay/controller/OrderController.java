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
 * @ClassName: OrderController
 * @Description:
 * @Author: ice
 * @Date: 2023/9/15 11:25
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Value("${server.port}")
    String post;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/get")
    public String get(String orderId) {
        // 注册到nacos,使用服务名称(pay-service)进行请求,restTemplate实例需要添加@LoadBalanced
        String payNo = restTemplate.getForObject("http://pay-service/pay/get", String.class);
        logger.info("订单服务查询支付流水号:{},post:{}", payNo, post);
        return "查询订单ID:"+orderId+",订单支付流水:"+ payNo;
    }

    @RequestMapping("/add")
    public String add(String orderId) {
        String payNo = restTemplate.getForObject("http://pay-service/pay/add", String.class);
        logger.info("创建订单:{},post:{}", payNo, post);
        return "创建订单:"+orderId+",订单支付流水:"+ payNo;
    }
}
