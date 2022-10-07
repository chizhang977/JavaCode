package com.cheng.rabbitmq.nine;

import com.cheng.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

public class Producer1 {

    public static final String SHOVEL2_EXCHANGE_NAME = "shovel2_exchange";

    public static final String QUEUE_Q2_NAME = "Q2";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        channel.queueDeclare(QUEUE_Q2_NAME,false,false,false,null);
        channel.exchangeDeclare(SHOVEL2_EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        channel.queueBind(QUEUE_Q2_NAME,SHOVEL2_EXCHANGE_NAME,"routekey.q2");
        channel.basicPublish(SHOVEL2_EXCHANGE_NAME,"routekey.q1",null,"hello".getBytes());
    }
}
