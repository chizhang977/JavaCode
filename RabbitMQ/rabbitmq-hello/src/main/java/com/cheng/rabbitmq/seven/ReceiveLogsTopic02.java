package com.cheng.rabbitmq.seven;


import com.cheng.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * 消费者:使用主题模式
 */
public class ReceiveLogsTopic02 {
    public static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        String queueName = "Q2";
        channel.queueDeclare(queueName,false,false,false,null);

        channel.queueBind(queueName,EXCHANGE_NAME,"*.*.rabbit");
        channel.queueBind(queueName,EXCHANGE_NAME,"lazy.#");

        System.out.println("准备接收消息"+queueName);
        DeliverCallback deliverCallback = (consumerTag,deliver)->{
            String message = new String(deliver.getBody(),"UTF-8");
            System.out.println("接受键为:"+deliver.getEnvelope().getRoutingKey()+"消息为:"+message);
        };
        channel.basicConsume(queueName,false,deliverCallback,consumerTag -> {});
        

    }
}
