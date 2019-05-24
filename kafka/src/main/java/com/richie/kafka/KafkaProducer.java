package com.richie.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;

/**
 * demo
 * <p>
 * Created by lylaut on 2019-05-21
 */
@Component
//@EnableScheduling
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 定时任务
     */
//    @Scheduled(cron = "00/1 * * * * ?")
    public void send() {
        for (int i = 0; i < 10000; ++i) {
            String message = UUID.randomUUID().toString() + String.format("-------%04d", i);
            ListenableFuture future = kafkaTemplate.send("test", message);
            future.addCallback(o -> System.out.println("send-消息发送成功：" + message), throwable -> System.out.println("消息发送失败：" + message));
        }
    }

}
