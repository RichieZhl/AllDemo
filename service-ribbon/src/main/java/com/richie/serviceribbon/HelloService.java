package com.richie.serviceribbon;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * demo
 *
 * Created by lylaut on 2019-05-27
 */
@Service
public class HelloService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "serviceFailure")
    public String getHelloContent() {
        return restTemplate.getForObject("http://SERVICE-HELLOWORLD/", String.class);
    }

    @HystrixCommand(fallbackMethod = "serviceFailure1")
    public City getCity(String name) {
        return restTemplate.postForObject("http://SERVICE-HELLOWORLD/city", name, City.class);
    }

    @HystrixCommand(fallbackMethod = "serviceFailure2")
    public City getCity1() {
        City city = new City();
        city.setId(6L);
        city.setProvinceId(4L);
        city.setCityName("hello");
        city.setDesc("哈哈");
        ResponseEntity<City> entity = restTemplate.postForEntity("http://SERVICE-HELLOWORLD/cityWithJson", city, City.class);
        return entity.getBody();
    }

    public String serviceFailure() {
        return "hello world service is not available !";
    }

    public City serviceFailure1(String name) {
        System.out.println(name);
        return new City();
    }

    public City serviceFailure2() {
        return new City();
    }
}
