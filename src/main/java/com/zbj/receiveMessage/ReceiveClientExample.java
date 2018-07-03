package com.zbj.receiveMessage;

import com.zhubajie.messagequeuesdk.consumer.ConsumeManager;
import com.zhubajie.messagequeuesdk.consumer.MessageListener;

public class ReceiveClientExample {

    public static void main(String[] args) throws Exception {
        MessageListener messageListener=new UserMessageListener();
        ConsumeManager consumeManager=new ConsumeManager();
        
        consumeManager.setThreadCount(2);
        consumeManager.setBizId(201709010001l);
        consumeManager.setQueue("wujiatest_201709010001");
        consumeManager.setListener(messageListener);

        consumeManager.start();

    }
}
