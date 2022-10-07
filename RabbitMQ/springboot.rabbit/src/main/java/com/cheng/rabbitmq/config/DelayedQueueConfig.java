package com.cheng.rabbitmq.config;

import com.rabbitmq.client.BuiltinExchangeType;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import sun.java2d.windows.GDIRenderer;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class DelayedQueueConfig {

    //延迟交换机
    public static final String DELAYED_EXCHANGE_NAME = "delayed_exchange";
    //延迟队列
    public static final String DELAYED_QUEUE_NAME = "delayed_queue";
    //routingkey
    public static final String DELAYED_ROUTING_KEY = "delayed.routingkey";

    //声明交换机(自定义交换机)
    @Bean("dExchange")
    public CustomExchange delayedExchange(){
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-delayed-type", "direct");
        return new CustomExchange(DELAYED_EXCHANGE_NAME, "x-delayed-message",true,false, arguments);
    }

    //声明队列
    @Bean("dQueue")
    public Queue delayedQueue(){
        return new Queue(DELAYED_QUEUE_NAME);
    }

    //进行绑定
    @Bean
    public Binding delayedQueueBindDelayedExchange(@Qualifier("dExchange") CustomExchange exchange,
                                                   @Qualifier("dQueue") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with(DELAYED_ROUTING_KEY).noargs();
    }

}
