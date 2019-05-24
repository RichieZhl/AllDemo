package com.richie.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * demo
 *
 * Created by lylaut on 2019-05-24
 */
@RefreshScope
@RestController
public class IndexController {

    @Value("${hello}")
    private String hello;

    @GetMapping("/hello")
    public String hello(){
        return hello;
    }
}
