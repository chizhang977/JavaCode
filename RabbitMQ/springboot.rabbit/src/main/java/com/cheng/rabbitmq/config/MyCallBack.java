package com.cheng.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
@Slf4j
public class MyCallBack implements RabbitTemplate.ConfirmCallback ,RabbitTemplate.ReturnCallback{

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    /**
     * 交换机回调方法，发送消息，成功失败就会回调
     * @param correlationData 消息的id以及相关信息
     * @param ack 交换机收到消息 true
     * @param cause 失败的原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
       String id =  correlationData!=null?correlationData.getId():"";

      if (ack){
          log.info("交换机已经收到消息id为:{}的消息",id);
      }else {
          log.info("交换机还未收到id:{}消息，原因:{}",id,cause);
      }
    }

    /**
     * 可以在消息传递的过程中不可达目的地将消息返回给生产者
     * 只有在不可达目的地的时候，才进行回调
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error("消息{}，被交换机：{}回退，原因为：{}，路由key{}",new String(message.getBody()),exchange,replyText,routingKey);
    }



}
