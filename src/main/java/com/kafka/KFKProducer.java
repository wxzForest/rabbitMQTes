package com.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.PartitionInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class KFKProducer {

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("bootstrap.servers", "bdata.kafka02.zbj:9092,bdata.kafka03.zbj:9092");
        //props.put("bootstrap.servers", "127.0.0.1:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        //生产者发送消息
        String topic = "java-monitor-cachecloud-server_topic_commond";
        //String value = "{\"id\":1,\"host\":\"172.26.24.3\",\"port\":6490,\"commond\":\"info\",\"description\":null}";
        String value="wxz123";
        Producer<String, String> procuder = new KafkaProducer<String,String>(props);
        ProducerRecord<String, String> msg = new ProducerRecord<String, String>(topic, value);
        procuder.send(msg);

        //列出topic的相关信息
//        List<PartitionInfo> partitions = new ArrayList<PartitionInfo>() ;
//        partitions = procuder.partitionsFor(topic);
//        for(PartitionInfo p:partitions)
//        {
//            System.out.println(p);
//        }
//
//        System.out.println("send message over.");
        procuder.close();
    }
}