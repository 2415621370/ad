package com.itcanteen.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/10 16:56
 */
@Slf4j
public class MyConsumer {

    private static KafkaConsumer<String,String> consumer;
    private static  Properties properties;
     static {
          properties = new Properties();
         properties.put("bootstrap.servers","10.211.55.8:9092");
         properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
         properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
         properties.put("group.id","test-consumer-group");
     }

    /**
     * 自动提交位移
     */
     public static  void test1(){
         properties.put("enable.auto.commit",true);
         consumer = new KafkaConsumer<>(properties);
         consumer.subscribe(Collections.singletonList("test1"));

         while(true){
             ConsumerRecords<String, String> consumerRecords = consumer.poll(100);
             for(ConsumerRecord consumerRecord:consumerRecords){
                 log.info(consumerRecord.topic());
                 log.info(consumerRecord.partition()+"");
                 log.info(consumerRecord.offset()+"");
                 log.info(consumerRecord.key()+"");
                 log.info(consumerRecord.value()+"");
             }

         }

     }

    /**
     * 手动同步提交位移
     */
     public static void test2(){
         properties.put("enable.auto.commit",false);
         consumer = new KafkaConsumer<>(properties);
         consumer.subscribe(Collections.singletonList("test0"));

         while (true){
             ConsumerRecords<String, String> consumerRecords = consumer.poll(100);
             for(ConsumerRecord consumerRecord:consumerRecords){
                 log.info(consumerRecord.topic());
                 log.info(consumerRecord.partition()+"");
                 log.info(consumerRecord.offset()+"");
                 log.info(consumerRecord.key()+"");
                 log.info(consumerRecord.value()+"");
             }

             //造成线程阻塞
             consumer.commitSync();


         }
     }


     public static void main(String[] args){
        // test1();
         test2();
     }
}
