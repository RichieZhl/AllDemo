package com.richie.springcloudstarterfeign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * demo
 *
 * Created by lylaut on 2019-05-27
 */
@RestController
public class IndexController {
    @Resource
    HelloService helloService;

    @GetMapping("/")
    public String getHome() {
        return "feign " + helloService.getHelloContent();
    }

    @GetMapping("/city")
    public City getCity() {
        return helloService.getCity("test");
    }

    @GetMapping("/city1")
    public City getCity1() {
        City city = new City();
        city.setId(6L);
        city.setProvinceId(4L);
        city.setCityName("hello");
        city.setDesc("哈哈");
        return helloService.getCity1(city);
    }
}
