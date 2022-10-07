package com.cheng.rabbitmq1.one;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    //队列名称
    public static final String QUEUE_NAME = "messageCheng";

    //发送消息
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.200.131");
        factory.setUsername("admin");
        factory.setPassword("123");

        //创建连接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();
        //消息内容
        String message = "Hello RabbitMQ";
        //创建队列
        /**
         * queeuDeclare:队列声明
         * 1、队列名称
         * 2、如果声明一个durable queue(持久队列),那么服务器重启之后仍然会存在
         * 3、该队列是否只是允许一个消费者进行消费
         * 4、自动删除队列（当没有消费者进行消费）
         * 5、其他参数给队列
         */
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        //发布消息
        /**
         * 1、那个交换机去发布，此程序没有考虑交换机
         * 2、路由的key，队列的名称
         * 3、其他参数
         * 4、消息体
         */
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.println("消息发送完毕");
    }
}
