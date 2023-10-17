package com.tx.pay.controller;

import com.alibaba.nacos.api.config.annotation.NacosProperty;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: OrderController
 * @Description:
 * @Author: ice
 * @Date: 2023/9/15 11:25
 */
@RestController
@RefreshScope
@RequestMapping("/nacos")
public class NacosController {

    private static final Logger logger = LoggerFactory.getLogger(NacosController.class);

    @Value("${user.name}")
    private String name;

    @Value("${user.age:18}")
    private int age;

    @Resource(name = "NacosUser")
    private List<String> likes;

    @RequestMapping("/echo/{key}")
    public String get(@PathVariable String key) {
        logger.info("查询nacos配置,key:{}", key);
        return "nacos配置,"+key+"="+("name".equals(key)? name : age);
    }
}
