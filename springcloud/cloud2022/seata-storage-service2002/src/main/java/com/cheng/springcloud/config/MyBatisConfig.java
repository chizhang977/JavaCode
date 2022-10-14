package com.cheng.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.cheng.springcloud.dao"})
public class MyBatisConfig {
}
