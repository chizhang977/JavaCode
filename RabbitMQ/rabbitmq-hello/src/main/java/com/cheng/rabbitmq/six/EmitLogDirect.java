package com.cheng.rabbitmq.six;

import com.cheng.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *生产者
 */
public class EmitLogDirect {

    public static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        //需要发送的消息
        Map<String,String> bindingKey = new HashMap<>();
        bindingKey.put("info","普通info消息");
        bindingKey.put("warning","警告信息");
        bindingKey.put("error","错误信息");
        bindingKey.put("debug","调试模式");

        Set<Map.Entry<String, String>> entries = bindingKey.entrySet();
        for (Map.Entry<String, String> mm: entries) {
            String key = mm.getKey();
            String value = mm.getValue();
            channel.basicPublish(EXCHANGE_NAME,key,null,value.getBytes("UTF-8"));
            System.out.println("生产者消息发出"+value);
        }

    }
}
