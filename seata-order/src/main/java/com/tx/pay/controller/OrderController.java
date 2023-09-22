package com.tx.pay.controller;

import com.tx.pay.dao.TestDao;
import com.tx.pay.exception.SeataGlobalException;
import com.tx.pay.feign.PayFeignService;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
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

    @Resource
    private TestDao testDao;

    @Autowired
    private PayFeignService payFeignService;

    @RequestMapping("/create")
    public String add(String name, BigDecimal amount) {
        logger.info("用户:{},获取金额:{}", name, amount);
        testDao.create(name, amount);
        payFeignService.add(name, amount);
        return "订单金额:" + amount;
    }

    @GlobalTransactional
//    @Transactional
    @RequestMapping("/ts")
    public String ts(String name, BigDecimal amount) {
        logger.info("用户:{},获取金额:{}", name, amount);
        testDao.create(name, amount);
        payFeignService.add(name, amount);
        int a = 1/0;
        return "订单金额:" + amount;
    }
}
