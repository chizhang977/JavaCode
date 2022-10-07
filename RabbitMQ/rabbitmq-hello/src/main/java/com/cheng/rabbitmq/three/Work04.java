package com.cheng.rabbitmq.three;

import com.cheng.rabbitmq.utils.RabbitMqUtils;
import com.cheng.rabbitmq.utils.SleepUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * 消息再手动应答时不丢失，放回队列中重新消费
 */
public class Work04 {
    public static final String TASK_QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        System.out.println("C2等待接受消息处理时间较长");

        //采用手动应答方式
        boolean autoAck = false;
        DeliverCallback deliverCallback = (consumerTag,  message)->{
            //沉睡1秒
            SleepUtils.sleep(30);
            System.out.println("接受到消息:"+new String(message.getBody(),"UTF-8"));
            //手动应答
            /**
             * 1、消息的标记tag
             * 2、是否批量应答：如果true则可能会消息丢失
             */
            channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
        };
        //不公平分发
        int prefetchCount = 5;
        channel.basicQos(prefetchCount);
        //消息消费
        channel.basicConsume(TASK_QUEUE_NAME,autoAck,deliverCallback, consumerTag -> {
            System.out.println(consumerTag+"消费者取消消费接口回调逻辑");
        });
    }
}
