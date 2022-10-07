package com.cheng.rabbitmq.six;

import com.cheng.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * 接收者：使用direct类型的交换机
 *
 */
public class ReceiveLogsDirect02 {
    //交换机名称
    public static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws Exception {
        //信道
        Channel channel = RabbitMqUtils.getChannel();

        //交换机（类型为直接）
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        //声明队列
        String queueName = "disc";
        channel.queueDeclare(queueName,false,false,false,null);

        //交换机和队列进行绑定
        channel.queueBind(queueName,EXCHANGE_NAME,"error");
        DeliverCallback deliverCallback = (ConsumerTag,delivery) ->{
            String message= new String(delivery.getBody(),"UTF-8");

            message = "接受键"+delivery.getEnvelope().getRoutingKey()+",消息为:"+message;

            File file = new File("D:\\work\\rabbitmq_info.txt");
            FileUtils.writeStringToFile(file,message,"UTF-8");
            System.out.println("错误消息已经接受");
        };
        CancelCallback cancelCallback = ConsumerTag ->{

        };
        //接收消息
        System.out.println("ReceiveLogsDirect02准备接收消息");
        channel.basicConsume(queueName,false,deliverCallback,cancelCallback);

    }

}
