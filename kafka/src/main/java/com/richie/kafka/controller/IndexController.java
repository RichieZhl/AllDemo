package com.richie.kafka.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.richie.kafkaconsumer.domain.City;
import com.richie.kafkaconsumer.dubbo.CityDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * demo
 * <p>
 * Created by lylaut on 2019-05-22
 */
@RestController
@RequestMapping("/api/")
public class IndexController {
    @Reference(version = "1.0.0", loadbalance = "Random")
    private CityDubboService cityDubboService;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "city", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "failBackMethod")
    public City getcity(@RequestParam String name) {
        City city = cityDubboService.findCityByName(name);
        try {
            String cityStr = objectMapper.writeValueAsString(city);
            ListenableFuture future = kafkaTemplate.send("test", cityStr);
            future.addCallback(o -> System.out.println("send-消息发送成功：" + name), throwable -> System.out.println("消息发送失败：" + name));
        } catch (Exception e) {
            return city;
        }

        return city;
    }

    public City failBackMethod(String name) {
        System.out.println(name + "service request fail");
        return null;
    }
}
