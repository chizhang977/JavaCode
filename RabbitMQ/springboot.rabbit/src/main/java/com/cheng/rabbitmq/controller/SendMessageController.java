package com.cheng.rabbitmq.controller;

import com.cheng.rabbitmq.config.DelayedQueueConfig;
import com.fasterxml.jackson.databind.DatabindException;
import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 生产者
 */
@Slf4j
@RestController
@RequestMapping("/ttl")
public class SendMessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/sendMsg/{message}")
    public void sendMsg(@PathVariable String message){
        log.info("当前时间:{},发送一条信息给两个TTL列队:{}",new Date().toString(),message);

        rabbitTemplate.convertAndSend("X","XA","消息来自ttl为10S的队列:"+message);

        rabbitTemplate.convertAndSend("X","XB","消息来自ttl为40S的队列:"+message);

    }

    @GetMapping("/sendExpirationMsg/{message}/{ttlTime}")
    public void sendMsg(@PathVariable String message ,@PathVariable String ttlTime){
        log.info("当前时间:{},发送一条时常：{},信息给TTl列队:{}",new Date().toString(),ttlTime,message);
        rabbitTemplate.convertAndSend("X","XC",message,msg->{
            //设置延迟时常
            msg.getMessageProperties().setExpiration(ttlTime);
            return msg;
        });

    }

    @GetMapping("sendDelayMsg/{message}/{delayTime}")
    public void sendMsg(@PathVariable String message,@PathVariable Integer delayTime){
        //发送消息
        log.info("当前时间：{}，发送一条时间:{},信息给延迟队列信息为：{}", new Date().toString(),delayTime,message);

        rabbitTemplate.convertAndSend(DelayedQueueConfig.DELAYED_EXCHANGE_NAME,
                                        DelayedQueueConfig.DELAYED_ROUTING_KEY,
                                        message,
                                        message1 -> {
                                            message1.getMessageProperties().setDelay(delayTime);
                                            return message1;
                                        });
        }


    }

