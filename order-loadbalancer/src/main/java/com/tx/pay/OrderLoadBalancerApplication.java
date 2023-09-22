package com.tx.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@RibbonClients(value = {
//        @RibbonClient(name = "pay-service", configuration = RibbonRandomRuleConfig.class)
//})
public class OrderLoadBalancerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderLoadBalancerApplication.class, args);
    }

}
