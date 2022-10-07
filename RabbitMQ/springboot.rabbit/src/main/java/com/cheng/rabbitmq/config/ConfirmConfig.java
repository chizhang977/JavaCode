package com.cheng.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConfirmConfig {
    //交换机
    public static final String CONFIRM_EXCHANGE_NAME = "confirm_exchange";
    //队列
    public static final String CONFIRM_QUEUE_NAME = "confirm_queue";
    //RoutingKey
    public static final String CONFIRM_ROUTING_KEY = "key1";

    //备份交换机
    public static final String BACK_EXCHANGE_NAME = "back_exchange";

    //备份队列
    public static final String BACK_QUEUE_NAME = "back_queue";
    public static final String WARNING_QUEUE_NAME = "warning_queue";

    //交换机声明----->确认交换机发送消息给备份交换机
    @Bean("confirmExchange")
    public DirectExchange confirmExchange(){
       /* return  ExchangeBuilder.directExchange(CONFIRM_EXCHANGE_NAME)
                .durable(true).withArgument("alternate-exchange",BACK_EXCHANGE_NAME).build();*/
        Map<String,Object> map = new HashMap<>();
        map.put("alternate-exchange",BACK_EXCHANGE_NAME);
        return new DirectExchange(CONFIRM_EXCHANGE_NAME,true,false,map);
    }

    //声明队列
    @Bean("confirmQueue")
    public Queue confirmQueue(){
        return QueueBuilder.durable(CONFIRM_QUEUE_NAME).build();
    }
    //banding
    @Bean
    public Binding queueBindingExchange(@Qualifier("confirmExchange") DirectExchange directExchange,
                                        @Qualifier("confirmQueue") Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with(CONFIRM_ROUTING_KEY);
    }

    //声明备份交换机
    @Bean("backupExchhange")
    public FanoutExchange backupExchange(){
        return new FanoutExchange(BACK_EXCHANGE_NAME);
    }

    //备份队列
    @Bean("backQueue")
    public Queue backupQueue(){
        return QueueBuilder.durable(BACK_QUEUE_NAME).build();
    }

    //警告队列
    @Bean("warningQueue")
    public Queue warningQueue(){
        return QueueBuilder.durable(WARNING_QUEUE_NAME).build();
    }

    @Bean
    public Binding backQueueBindBackExchange(@Qualifier("backupExchhange") FanoutExchange exchange,
                                                    @Qualifier("backQueue") Queue backQueue){
        return BindingBuilder.bind(backQueue).to(exchange);
    }

    @Bean
    public Binding WarnQueueBindBackExchange(@Qualifier("backupExchhange") FanoutExchange exchange,
                                                    @Qualifier("warningQueue") Queue warningQueue){
        return BindingBuilder.bind(warningQueue).to(exchange);
    }
}
