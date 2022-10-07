package com.cheng.rabbitmq.controller;


import com.cheng.rabbitmq.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/confirm")
public class ProduceController {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMessage/{message}")
    public void sendMsg(@PathVariable String message){
        CorrelationData correlationData = new CorrelationData("1");

        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE_NAME
                ,ConfirmConfig.CONFIRM_ROUTING_KEY+"23",message,correlationData);
        log.info("发送的消息：{}",message);


        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE_NAME
                ,ConfirmConfig.CONFIRM_ROUTING_KEY,message,correlationData);
        log.info("发送的消息：{}",message);
    }


}
