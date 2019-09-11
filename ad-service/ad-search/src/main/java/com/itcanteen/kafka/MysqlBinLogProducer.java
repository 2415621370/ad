package com.itcanteen.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/11 16:18
 */

@Slf4j
@Component
public class MysqlBinLogProducer {

    //消息的生产者对象
    private static KafkaProducer<String,String> producer;

    static {
        Properties properties = new Properties();
        properties.put("bootstrap.servers","10.211.55.8:9092");
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        producer= new KafkaProducer<String, String>(properties);
    }


    public  void send(String message){
        ProducerRecord<String, String> stringStringProducerRecord =
                new ProducerRecord<>("test1", System.currentTimeMillis()+"", message);

        producer.send(stringStringProducerRecord,new MyProducerCallBack());



    }


    /**
     * 以异步回调的方式，发送消息
     */
    private static class MyProducerCallBack implements Callback {

        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            if(e!=null){
                e.printStackTrace();
                return;
            }

            log.info("recordMetadata.topic->{}",recordMetadata.topic());
            log.info("recordMetadata.partition->{}",recordMetadata.partition());
            log.info("recordMetadata.offset->{}",recordMetadata.offset());

            log.info("=============OK=");


        }
    }



}
