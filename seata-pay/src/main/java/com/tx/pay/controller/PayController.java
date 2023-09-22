package com.tx.pay.controller;

import com.tx.pay.dao.TestDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;

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

    @Resource
    private TestDao testDao;

    @RequestMapping("/create")
    public String create(String name, BigDecimal amount) {
        logger.info("用户:{},支付金额:{}", name, amount);
        testDao.create(name, amount);
        return "支付金额:" + amount;
    }
}
