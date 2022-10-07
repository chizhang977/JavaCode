package com.cheng.rabbitmq.five;

import com.cheng.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * 消息的接受
 */
public class ReceiveLogs01 {
    //交换机的名称
    public static final String EXCHANGES_NAME = "logs";

    //接收消息
    public static void main(String[] args) {
        try {
            //声明信道
            Channel channel = RabbitMqUtils.getChannel();

            //声明交换机
            channel.exchangeDeclare(EXCHANGES_NAME,"fanout");

            //声明临时队列
            String queue = channel.queueDeclare().getQueue();

            //交换机和队列进行绑定
            channel.queueBind(queue,EXCHANGES_NAME,"");

            DeliverCallback deliverCallback = ( consumerTag, delivery) ->{
                String message = new String(delivery.getBody(),"UTF-8");
                System.out.println("ReceiveLogs01控制台接收到的消息为:"+message);
            };
            CancelCallback callback = consumerTag -> {};
            //接收消息
            channel.basicConsume(queue,false,deliverCallback,callback);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
