package com.tx.pay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.tx.pay.dao")
public class SeataPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataPayApplication.class, args);
    }

}
