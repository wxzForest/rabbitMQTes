package com.smart;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Receiver extends MQDao{

    /**
     *  consumer(消费者)端步骤：
     (1)：创建ConnectionFactory，并且设置一些参数，比如hostname,portNumber等等
     (2)：利用ConnectionFactory创建一个Connection连接
     (3)：利用Connection创建一个Channel通道
     (4)：将queue和Channel进行绑定，注意这里的queue名字要和前面producer创建的queue一致
     (5)：创建消费者Consumer来接收消息，同时将消费者和queue进行绑定
     */
    public static void receive(){
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
//            channel.queueDeclare(QUEUE_NAME,true,false,false,null);
//            Consumer consumer=new DefaultConsumer(channel){
//                @Override
//                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                    System.out.println("准备接收消息");
//                    String message=new String(body,"utf-8");
//                    System.out.println("接收到消息:"+message);
//                }
//            };
            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(QUEUE_NAME,true,consumer);
            System.out.println("连接成功！");
            while (true){
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                System.out.println("[" + message + "]");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
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
        receive();
    }
}
