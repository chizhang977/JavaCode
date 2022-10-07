package com.cheng.springcloud.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;


@Configuration
public class FeignLoggerLevel {
    @Bean
    public Logger.Level getFeignLevel(){
        return Logger.Level.FULL;
    }
}
