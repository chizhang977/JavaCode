package com.cheng.rabbitmq.four;

import com.cheng.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.client.MessageProperties;

import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 验证发布确认：使用的时间来比较那个确认方式是最好的
 *  单个确认
 *  批量确认
 *  异步确认
 */
public class ConfrimMessage {
    //批量发消息的个数
    public static final int MESSAGE_COUNT = 1000;

    public static void main(String[] args) throws Exception {

        //单个确认
       // ConfrimMessage.publicMessageIndividualy();//2111ms
        //批量确认
       // publicMessageBat();//165ms
        //异步确认
        publicMessageAsync();//54ms
    }

    /**
     * 单个确认发布
     * @throws Exception
     */
    public static void  publicMessageIndividualy() throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName,true,false,false,null);

        //开启发布确认
        channel.confirmSelect();
        //开始时间
        long begin = System.currentTimeMillis();
        //发布
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String  message = i + "";
            channel.basicPublish("",queueName, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());

            //单个消息确认
            boolean flag = channel.waitForConfirms();
            if (flag){
                System.out.println("消息发送成功");
            }
        }
        long end = System.currentTimeMillis();

        System.out.println("发布"+MESSAGE_COUNT+"条单独确认消息，耗时:"+(end-begin)+"ms");


    }

    /**
     * 批量发布
     * @throws Exception
     */
    public static void publicMessageBat() throws Exception{
        Channel channel = RabbitMqUtils.getChannel();

        //声明队列
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName,true,false,false,null);

        //开启发布确认
        channel.confirmSelect();

        long begin = System.currentTimeMillis();

        //批量确认消息
        int batchSize = 100;

        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("",queueName,MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());

            if (i % batchSize == 0 ){
                channel.waitForConfirms();
                System.out.println("确认消息:"+i);
            }

        }
        long end = System.currentTimeMillis();
        System.out.println("发布"+MESSAGE_COUNT+"条批量确认消息，耗时:"+(end-begin)+"ms");
    }

    /**
     * 异步发布
     */
    public static void publicMessageAsync()throws Exception{
        //声明一个信道
        Channel channel = RabbitMqUtils.getChannel();
        String queueName = UUID.randomUUID().toString();
        //声明队列
        channel.queueDeclare(queueName,true,false,false,null);

        //开始确认发布
        channel.confirmSelect();

        /**
         * 线程有序的哈希表适用于高并发的情况
         * 1、能轻松的将序号与消息进行关联key,value
         * 2、轻松的批量删除条数
         * 3、支持高并发
         */
        ConcurrentSkipListMap<Long,String> outstandingConfirms = new ConcurrentSkipListMap<>();


        //成功
        ConfirmCallback callback = ( deliveryTag,  multiple) ->{
            if (multiple){
                //2、删除确认消息
                ConcurrentNavigableMap<Long, String> confirmMap = outstandingConfirms.headMap(deliveryTag);
            }else {
                outstandingConfirms.remove(deliveryTag);
            }
            System.out.println("确认的消息:"+deliveryTag);

        };
        //失败
        /**
         * 消息标记
         * 是否为批量
         */
        ConfirmCallback nackcallback =( deliveryTag,  multiple) ->{
            //3、打印未确认消息
            String message = outstandingConfirms.get(deliveryTag);
            System.out.println("未确认的消息时:"+message+"未确认的消息:"+deliveryTag);
        };
        //消息监听器，监听那些成功，那些失败
        /**
         * 监听消息成功
         * 监听那些消息失败
         */
        channel.addConfirmListener(callback,nackcallback);
        long begin = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("",queueName,null,message.getBytes());
            //1、记录下所有发送的消息，消息总和
            outstandingConfirms.put(channel.getNextPublishSeqNo(),message);
        }



        long end = System.currentTimeMillis();
        System.out.println("发布"+MESSAGE_COUNT+"条异步确认消息，耗时:"+(end-begin)+"ms");
    }

}
