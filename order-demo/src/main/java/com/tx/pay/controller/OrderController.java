package com.tx.pay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/get")
    public String get(String orderId) {
        logger.info("查询订单号,id:{}", orderId);
        return "订单:"+orderId+",订单号:"+ UUID.randomUUID();
    }

    @RequestMapping("/add")
    public String add(String orderId) {
        logger.info("创建订单号,id:{}", orderId);
        return "创建订单:"+orderId+",订单号:"+ UUID.randomUUID();
    }
}
