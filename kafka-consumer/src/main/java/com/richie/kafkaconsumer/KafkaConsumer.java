package com.richie.kafkaconsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * demo
 * <p>
 * Created by lylaut on 2019-05-21
 */
@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"test"})
    public void receive(ConsumerRecord<?, ?> record) {
        String value = (String) record.value();
        System.out.println("app_log--消费消息:" + value + ",thread:" + Thread.currentThread().getName());
    }
}