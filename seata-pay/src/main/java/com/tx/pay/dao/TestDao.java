package com.tx.pay.dao;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface TestDao {
    int create(@Param("name") String name, @Param("amount") BigDecimal amount);
}
