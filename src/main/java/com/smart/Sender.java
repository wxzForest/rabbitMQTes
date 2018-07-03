package com.smart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Sender extends MQDao{

    /**
     *    producer(生产者)端步骤：
     (1)：创建ConnectionFactory，并且设置一些参数，比如hostname,portNumber等等
     (2)：利用ConnectionFactory创建一个Connection连接
     (3)：利用Connection创建一个Channel通道
     (4)：创建queue并且和Channel进行绑定
     (5)：创建消息，并且发送到队列中
     注意，在我们当前的例子中，并没有用到exchange交换机，RabbitMQ默认情况下是会创建一个空字符串名字的exchange的，
          如果我们没有创建自己的exchange的话，默认就是使用的这个exchange；
     */
    public static void send(){
        ConnectionFactory factory=null;
        Connection connection=null;
        Channel channel=null;

        try {
            factory=new ConnectionFactory();
            factory.setUsername(USERNAME);
            factory.setPassword(PASSWORD);
            factory.setHost(HOST);
            factory.setPort(PORT);
            factory.setVirtualHost(VHOST);
            connection=factory.newConnection();
            channel=connection.createChannel();
//            Map map=new HashMap();
//            map.put("x-max-length-bytes",1048576l);
//            map.put("x-overflow","reject-publish");
//            channel.queueDeclare(QUEUE_NAME,true,false,false,map);
            String message="Hello MQ411";
            //topic模式，如果不加ROUTINGKEY，topic模式广播
            channel.basicPublish(EXCHANGE,ROUTINGKEY,null,message.getBytes("UTF-8"));
            System.out.println("消息已发送成功"+message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            try {
                channel.close();
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }

        }

    }


    public static void main(String[] args) {
        send();
    }

}
