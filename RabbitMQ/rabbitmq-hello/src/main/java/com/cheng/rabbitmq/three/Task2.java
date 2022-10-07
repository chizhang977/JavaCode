package com.cheng.rabbitmq.three;

import com.cheng.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.util.Scanner;

/**
 * 消息在手动应答时不丢失，放回队列中重新消费
 */
public class Task2 {
    //
    public static final String TASK_QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        boolean durable = true;//持久化
        //声明队列
        channel.queueDeclare(TASK_QUEUE_NAME,durable,false,false,null);

        //开启发布确认
        channel.confirmSelect();

        //从控制台输入
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String message = scanner.next();
            channel.basicPublish("",TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes("utf-8"));
            System.out.println("生产者发出消息："+message);
        }
    }
}
