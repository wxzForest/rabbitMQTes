package com.zbj.receiveMessage;

import com.zhubajie.messagequeuesdk.consumer.ConsumeResult;
import com.zhubajie.messagequeuesdk.consumer.MessageListener;
import com.zhubajie.messagequeuesdk.message.MessageExt;

public class UserMessageListener implements MessageListener{
    public ConsumeResult recevieMessage(MessageExt messageExt) {
        System.out.println(messageExt.getContent());
        return ConsumeResult.SUCCESS;
    }
}
