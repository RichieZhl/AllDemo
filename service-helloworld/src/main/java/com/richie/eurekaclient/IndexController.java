package com.richie.eurekaclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * demo
 *
 * Created by lylaut on 2019-05-24
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/")
    public String home() {
        return "hello world from port " + port;
    }

    private static Long id;

    @RequestMapping(value = "city", method = RequestMethod.POST)
    public City getCity(@RequestBody(required = false) String desc) {
        if (id == null) {
            id = 0L;
        }
        return new City(++id, 2L, "cityname:"+ ++id, desc == null ? "asdfadfasdf" : desc);
    }

    @RequestMapping(value = "cityWithJson", method = RequestMethod.POST)
    public City getCity1(@RequestBody City city) {

        return city;
    }
}
