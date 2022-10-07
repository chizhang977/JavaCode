package com.cheng.rabbitmq.six;

import com.cheng.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * 接收者：使用direct类型的交换机
 *
 */
public class ReceiveLogsDirect01 {
    //交换机名称
    public static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws Exception {
        //信道
        Channel channel = RabbitMqUtils.getChannel();

        //交换机（类型为直接）
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        //声明队列
        String queueName = "console";
        channel.queueDeclare(queueName,false,false,false,null);

        //交换机和队列进行绑定
        channel.queueBind(queueName,EXCHANGE_NAME,"info");
        channel.queueBind(queueName,EXCHANGE_NAME,"warning");

        DeliverCallback deliverCallback = (ConsumerTag,delivery) ->{
            String message= new String(delivery.getBody(),"UTF-8");
            System.out.println("接受的接受键为:"+delivery.getEnvelope().getRoutingKey()+"接收到消息为:"+message);
        };
        CancelCallback cancelCallback = ConsumerTag ->{

        };
        //接收消息
        System.out.println("ReceiveLogsDirect01准备接收消息");
        channel.basicConsume(queueName,false,deliverCallback,cancelCallback);

    }

}
