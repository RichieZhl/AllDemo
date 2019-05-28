package com.richie.serviceribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * demo
 *
 * Created by lylaut on 2019-05-27
 */
@RestController
public class IndexController {
    @Autowired
    HelloService helloService;

    @GetMapping("/")
    public String getHome() {
        return "ribbon " + helloService.getHelloContent();
    }

    @GetMapping("/city")
    public City getCity() {
        return helloService.getCity("test");
    }

    @GetMapping("/city1")
    public City getCity1() {
        return helloService.getCity1();
    }
}
