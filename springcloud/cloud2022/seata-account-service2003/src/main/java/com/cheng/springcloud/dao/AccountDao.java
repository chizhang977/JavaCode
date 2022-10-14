package com.cheng.springcloud.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.math.BigDecimal;

@MapperScan
public interface AccountDao {
    void decrease(@Param("userId") Long userId,@Param("money") BigDecimal money);
}
