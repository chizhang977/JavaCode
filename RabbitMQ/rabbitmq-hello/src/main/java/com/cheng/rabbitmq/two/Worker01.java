package com.cheng.rabbitmq.two;


import com.cheng.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;

/**
 * 工作线程相当于消费者
 */
public class Worker01 {
    //队列名称
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();

        System.out.println("c2等待接受消息....");
        //消息接收
        channel.basicConsume(QUEUE_NAME,true,(consumerTag,message)->{
            System.out.println("接收到的消息"+new String(message.getBody()));
        },(consumerTag)->{
            System.out.println(consumerTag+"消息接受取消");
        });
    }
}
