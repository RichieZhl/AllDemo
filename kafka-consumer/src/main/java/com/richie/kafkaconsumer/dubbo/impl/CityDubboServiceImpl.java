package com.richie.kafkaconsumer.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.richie.kafkaconsumer.domain.City;
import com.richie.kafkaconsumer.dubbo.CityDubboService;

import java.util.Random;

/**
 * 城市业务 Dubbo 服务层实现层
 * <p>
 * Created by bysocket on 28/02/2017.
 */
// 注册为 Dubbo 服务
@Service(version = "1.0.0", timeout = 10000, interfaceClass = CityDubboService.class)
public class CityDubboServiceImpl implements CityDubboService {
    private static Long count = 0L;

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    @Override
    public City findCityByName(String cityName) {
        int nextInt = new Random().nextInt(2000);
        System.out.println("sleep " + nextInt + "ms");
        try {
            Thread.sleep(nextInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new City(++count, 2L, cityName, "是我的故乡");
    }
}
