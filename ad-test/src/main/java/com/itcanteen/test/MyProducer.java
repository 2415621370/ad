/*
package com.itcanteen.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

*/
/**
 *
 * 消息的生产者
 * @author baimugudu
 * @email 2415621370@qq.com
 * @date 2019/9/10 15:54
 *//*

@Slf4j
public class MyProducer {

    //消息的生产者对象
    private static KafkaProducer<String,String> producer;

    static {
        Properties properties = new Properties();
        properties.put("bootstrap.servers","10.211.55.8:9092");
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        producer= new KafkaProducer<String, String>(properties);
    }


    */
/**
     * 只管发送，不管结果。一旦发生异常，造成数据丢失
     * 效率高
     *//*

    public static  void test1(){
        ProducerRecord<String, String> stringStringProducerRecord =
                new ProducerRecord<>("test0", "name", "1701B2121");
        producer.send(stringStringProducerRecord);
        producer.close();
    }

    */
/**
     * 以同步的方式发送，得到返回的数据
     * @throws ExecutionException
     * @throws InterruptedException
     *//*


    public static void test2() throws ExecutionException, InterruptedException {
        ProducerRecord<String, String> stringStringProducerRecord =
                new ProducerRecord<>("test1", "name1", "1701B1");
        RecordMetadata recordMetadata = producer.send(stringStringProducerRecord).get();
        System.out.println(recordMetadata.topic());//话题
        System.out.println(recordMetadata.partition());//分区
        System.out.println(recordMetadata.offset());//偏移量 （id）
        producer.close();
    }


    public static void test3(){
        ProducerRecord<String, String> stringStringProducerRecord =
                new ProducerRecord<>("test1", "name2", "1701B2");

        producer.send(stringStringProducerRecord,new MyProducerCallBack());


        stringStringProducerRecord =
                new ProducerRecord<>("test1", "name3", "1701B3");
        producer.send(stringStringProducerRecord,new MyProducerCallBack());

        stringStringProducerRecord =
                new ProducerRecord<>("test1", "name4", "1701B4");
        producer.send(stringStringProducerRecord,new MyProducerCallBack());


        stringStringProducerRecord =
                new ProducerRecord<>("test1", "name4", "1701B4");
        producer.send(stringStringProducerRecord,new MyProducerCallBack());

        stringStringProducerRecord =
                new ProducerRecord<>("test1", "name4", "1701B4");
        producer.send(stringStringProducerRecord,new MyProducerCallBack());
    }

    */
/**
     * 以异步回调的方式，发送消息
     *//*

    private static class MyProducerCallBack implements Callback{

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

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();
      // test2();
       // test3();
    }


}
*/
