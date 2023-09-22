package com.tx.pay.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.tx.pay.feign.PayFeignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: OrderController
 * @Description:
 * @Author: ice
 * @Date: 2023/9/15 11:25
 */
@RestController
@RequestMapping("/power")
public class SentinelOpenfeignController {

    private static final Logger logger = LoggerFactory.getLogger(SentinelOpenfeignController.class);

    @Qualifier("com.tx.pay.feign.PayFeignService")
    @Autowired
    private PayFeignService payFeignService;

    @RequestMapping("/open")
    public String error(String name) {
        logger.info("需要定义一个处理类MyBlockExceptionHandler:{}", name);
        String pay = payFeignService.error(name);
        return "支付:" + pay;
    }
}
