package com.cheng.rabbitmq1.one;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Delivery;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 */
public class Consumer {

    public static final String QUEUE_NAME = "messageCheng";

    //消费
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.200.131");
        factory.setUsername("admin");
        factory.setPassword("123");

        //创建连接
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //接受消息
        /**
         * 1、队列名称、
         * 2、消费成功之后是否要自动应答：true自动应答，true手动应答
         * 3、消息传递时的回调
         * 4、消费者取消时的回调
         */
        channel.basicConsume(QUEUE_NAME,true,( consumerTag,  message)->{
            System.out.println(new String(message.getBody()));
        }, consumerTag -> {
            System.out.println("消息消费被中断");
        });


    }
}
