package com.cheng.rabbitmq.eight;


import com.cheng.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

/**
 * 生产者
 */
public class Producer {

    public static final String NORMAL_EXCHANGE = "normal_exchange";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();

        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);

        //为了让信息成为死信的队列，需要设置TTL过期时间
       /* AMQP.BasicProperties properties = new
                AMQP.BasicProperties().builder().expiration("10000").build();*/

        for (int i = 1; i < 11; i++) {
            String message = i + "";
            channel.basicPublish(NORMAL_EXCHANGE,"zhangsan",null,message.getBytes("UTF-8"));
            System.out.println("生产者发送消息:"+message);
        }
    }
}
