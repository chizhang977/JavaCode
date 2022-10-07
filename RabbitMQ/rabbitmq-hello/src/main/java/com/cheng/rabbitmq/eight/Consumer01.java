package com.cheng.rabbitmq.eight;

import com.cheng.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * 死信队列
 */
public class Consumer01 {
    //正常交换机
    public static final String NORMAL_EXCHANGE = "normal_exchange";
    //死信交换机
    public static final String DEAD_EXCHANGE = "dead_exchange";
    //正常队列
    public static final String NORMAL_QUEUE = "normal_queue";
    //死信队列
    public static final String DEAD_QUEUE = "dead_queue";

    //接受消息
    public static void main(String[] args) throws Exception {
        //声明信道
        Channel channel = RabbitMqUtils.getChannel();

        //声明交换机
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(DEAD_EXCHANGE,BuiltinExchangeType.DIRECT);

        //声明队列
        channel.queueDeclare(DEAD_QUEUE,false,false,false,null);

        channel.queueBind(DEAD_QUEUE,DEAD_EXCHANGE,"lisi");

        Map<String ,Object> params = new HashMap<>();
        params.put("x-dead-letter-exchange",DEAD_EXCHANGE);
        params.put("x-dead-letter-routing-key","lisi");
       // params.put("x-max-length",6);
        channel.queueDeclare(NORMAL_QUEUE,false,false,false,params);

        //正常队列和私信队列进行绑定
        //binding
        channel.queueBind(NORMAL_QUEUE,NORMAL_EXCHANGE,"zhangsan");


        System.out.println("c1等待接受消息");
        DeliverCallback deliverCallback = (consumerTag,deliver) -> {
            String message = new String(deliver.getBody(),"UTF-8");
            if (message.equals("5")){
                System.out.println("消息:"+message+"已经被拒绝了，可能会在死信队列中");
                //requeue为false 表示拒绝重新入d队
                channel.basicReject(deliver.getEnvelope().getDeliveryTag(),false);
            }else {
                System.out.println("消息接受"+message);
                channel.basicAck(deliver.getEnvelope().getDeliveryTag(),false);
            }
            System.out.println("接受键"+deliver.getEnvelope().getRoutingKey()+"消息:"+message);
        };
        //接受消息
        channel.basicConsume(NORMAL_QUEUE,true,deliverCallback,consumerTag -> {});


    }

}
