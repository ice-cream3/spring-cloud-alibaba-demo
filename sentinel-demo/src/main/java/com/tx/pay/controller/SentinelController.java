package com.tx.pay.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class SentinelController {

    private static final Logger logger = LoggerFactory.getLogger(SentinelController.class);

    private static final String RESOURCE_NAME = "GET";
    private static final String USER_RESOURCE_NAME = "USER";
    private static final String ERROR_RESOURCE_NAME = "ERROR";
    private static final String DEGRADE_RESOURCE_NAME = "DEGRADE";

    @RequestMapping("/get")
    public String get(String orderId) {
        logger.info("限流订单号,id:{}", orderId);
        Entry entry = null;
        try {
            entry = SphU.entry(RESOURCE_NAME);

        } catch (BlockException e) {
            logger.info("get接口会限流了");
            return "查询订单方法被限流了,订单号:" + orderId;
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
        return "返回信息:" + orderId + ",订单号:" + UUID.randomUUID();
    }

    @RequestMapping("/user")
    @SentinelResource(value = USER_RESOURCE_NAME, blockHandler = "userBlock")
    public String user(String name) {
        logger.info("name:{}", name);
        return "你好:"+name;
    }

    /**
     * 统一异常处理
     * 如果接口添加 SentinelResource注解,则统一处理失效
     */
    @RequestMapping("/common")
    public String error(String name) {
        logger.info("需要定义一个处理类MyBlockExceptionHandler:{}", name);
        return "公共限流处理:"+name;
    }

    public String userBlock(String name, BlockException e) {
        logger.error("限流了!!!", e);
        return "限流!!!";
    }

    @PostConstruct
    private static void initFlowRules() {
        // 流控规则
        List<FlowRule> rules = new ArrayList<FlowRule>();
        // 流控
        FlowRule flowRule = new FlowRule();
        // 设置流控规则QPS
        flowRule.setRefResource(RESOURCE_NAME);
        // 设置受保护的资源阈值
        // limit QPS to 10
        flowRule.setCount(1);
        rules.add(flowRule);
    }
}
