package com.cheng.springcloud.service;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;



@Component
@EnableBinding(Sink.class)
public class MessageConsumerService {

    @Value("${server.port}")
    private String serverPort;


    @StreamListener(Sink.INPUT)
    public void basicConsuemr(Message message){
        System.out.println("接收到消息:"+message.getPayload()+"端口号"+serverPort);
    }
}
