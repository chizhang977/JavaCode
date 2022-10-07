package com.cheng.rabbitmq.one;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 */
public class Consumer {
    public static final String QUEUE_NAME = "hello";

    //接收消息
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工程
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("192.168.200.131");
        factory.setUsername("admin");
        factory.setPassword("123");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明 接受消息
        DeliverCallback deliverCallback = (consumerTag,message)->{
            System.out.println(new String(message.getBody()));
        };
        //取消消息
        CancelCallback callback = consumerTag -> {
            System.out.println("消息消费被中断");
        };
        /**
         * 消费者接受消息
         *
         * 1、表示消费那个队列
         * 2、消费成功之后是否要自动应答：true自动应答，true手动应答
         * 3、消费者未成功消费回调
         * 4、消费者取消消费的回调
         */
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,callback);

    }
}
