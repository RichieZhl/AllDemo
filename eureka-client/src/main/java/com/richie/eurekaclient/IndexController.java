package com.richie.eurekaclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
