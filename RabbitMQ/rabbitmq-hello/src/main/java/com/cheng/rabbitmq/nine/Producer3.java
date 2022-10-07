package com.cheng.rabbitmq.nine;

import com.cheng.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

public class Producer3 {

    public static final String FED_EXCHANGE_NAME = "fed_exchange";

    public static final String NODE2_QUEUE_NAME = "node1_queue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        channel.queueDeclare(NODE2_QUEUE_NAME,false,false,false,null);
        channel.exchangeDeclare(FED_EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        channel.queueBind(NODE2_QUEUE_NAME,FED_EXCHANGE_NAME,"r1");
        channel.basicPublish(FED_EXCHANGE_NAME,"r1",null,"hello".getBytes());
    }
}
