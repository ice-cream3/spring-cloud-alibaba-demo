package com.tx.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SentinelOpenfeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelOpenfeignApplication.class, args);
    }

}
