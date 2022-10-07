package com.cheng.rabbitmq.seven;


import com.cheng.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 生产者
 */
public class EmitLogTopic {
    public static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        Map<String,String> map = new HashMap<>();
        map.put("quick.orange.rabbit","被队列 Q1Q2 接收到");
        map.put("lazy.orange.elephant","被队列 Q1Q2 接收到");
        map.put("quick.orange.fox","被队列 Q1 接收到");
        map.put("lazy.brown.fox"," 被队列 Q2 接收到");
        map.put("lazy.pink.rabbit","虽然满足两个绑定但只被队列 Q2 接收一次");
        map.put("quick.brown.fox"," 不匹配任何绑定不会被任何队列接收到会被丢弃");
        map.put(" quick.orange.male.rabbit","是四个单词不匹配任何绑定会被丢弃");
        map.put("lazy.orange.male.rabbit","是四个单词但匹配 Q2");

        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> mm : entries){
            String key = mm.getKey();
            String value = mm.getValue();
            channel.basicPublish(EXCHANGE_NAME,key,null,value.getBytes("UTF-8"));
            System.out.println("生产发送消息："+value);
        }

    }
}
