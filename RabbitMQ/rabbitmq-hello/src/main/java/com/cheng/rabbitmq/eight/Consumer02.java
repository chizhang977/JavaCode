package com.cheng.rabbitmq.eight;

import com.cheng.rabbitmq.utils.RabbitMqUtils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

public class Consumer02 {
    public static final String DEAD_QUEUE = "dead_queue";


    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        System.out.println("准备接受消息");
        DeliverCallback callback  = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(),"UTF-8");
            System.out.println("123456接受消息为:"+message);
        };

        channel.basicConsume(DEAD_QUEUE,true,callback,consumerTag -> {});
    }
}
