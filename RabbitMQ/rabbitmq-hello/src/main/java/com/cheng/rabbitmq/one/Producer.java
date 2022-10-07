package com.cheng.rabbitmq.one;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 *
 * 生产者：发消息
 */
public class Producer {
    //队列名称
    public static final String QUEUE_NAME = "hello";

    //发消息
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //工厂的IP：连接rabbitmq的队列
        factory.setHost("192.168.200.131");
        //user/pass
        factory.setUsername("admin");
        factory.setPassword("123");
        //创建连接
        Connection connection = factory.newConnection();
        //获取信道
        Channel channel = connection.createChannel();
        //创建队列
        /**
         * 队列名称
         * 队列里面消息是否持久化，默认消息存储到内存中
         * 该队列是否只供一个消费者进行消费是否进行消息共享，true可以多个消费者消费，false，只能一个消费者消费
         *是否自动删除，最后一个消费者断开连接后，该队列是否自动删除，true自动删除，false，不会自动删除
         *其他参数:延迟
         */
        Map<String,Object> map = new HashMap<>();
        map.put("x-max-priority",10);
        channel.queueDeclare(QUEUE_NAME,true,false,false,map);

        for (int i = 0; i < 10; i++) {
            String message = "info" + i;
            if (i== 5){
                AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().priority(5).build();
                channel.basicPublish("",QUEUE_NAME,properties,message.getBytes());
            }else {
                channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            }
        }


        /**
         * 1、发送到那个交换机，没有考虑，null
         * 2、路由的key，本次是队列的名称
         * 3、其他参数信息
         * 4、消息体
         */

        System.out.println("消息发送完毕");
    }
}
