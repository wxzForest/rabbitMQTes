package com.zbj.publishMessage;

import com.zhubajie.messagequeuesdk.message.Message;
import com.zhubajie.messagequeuesdk.publisher.Publisher;

public class PublishClientExample {

    public static void main(String[] args) throws Exception {
        Publisher publisher=new Publisher();
        publisher.setBizId(201709040001L);
        publisher.init();
        Message message=new Message();
        message.setRoutingKey("crmpro.modify.routingKey");
        message.setExchange("crmpro.modify.exchange");
        message.setContent("恭喜你收到了此条消息！！！");
        publisher.publish(message);
    }
}
