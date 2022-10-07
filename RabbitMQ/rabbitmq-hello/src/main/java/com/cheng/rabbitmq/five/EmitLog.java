package com.cheng.rabbitmq.five;


import com.cheng.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.util.Scanner;

/**
 * 生产者
 */
public class EmitLog {
    public static final String EXCHANGES_NAME = "logs";


    //发送消息
    public static void main(String[] args) {

        try {
            //声明信道
            Channel channel = RabbitMqUtils.getChannel();
            //声明交换机
            channel.exchangeDeclare(EXCHANGES_NAME,"fanout");
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                String message = scanner.next();
                channel.basicPublish(EXCHANGES_NAME,"",null,message.getBytes("UTF-8"));
                System.out.println("生产者发出消息:"+message);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
