package com.cheng.rabbitmq.nine;


import com.cheng.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

public class Producer {
   public static final String SHOVEL1_EXCHANGE_NAME = "shovel1_exchange";

   public static final String QUEUE_Q1_NAME = "Q1";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        channel.queueDeclare(QUEUE_Q1_NAME,false,false,false,null);
        channel.exchangeDeclare(SHOVEL1_EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        channel.queueBind(QUEUE_Q1_NAME,SHOVEL1_EXCHANGE_NAME,"r1");
        channel.basicPublish(SHOVEL1_EXCHANGE_NAME,"r1",null,"hello".getBytes());
    }
}
