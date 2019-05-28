package com.richie.springcloudstarterfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * demo
 *
 * Created by lylaut on 2019-05-27
 */
@FeignClient(value = "SERVICE-HELLOWORLD", fallback = HelloServiceFailure.class)
public interface HelloService {
    @GetMapping("/")
    public String getHelloContent();

    @RequestMapping(value = "city", method = RequestMethod.POST)
    public City getCity(@RequestBody(required = false) String desc);

    @RequestMapping(value = "cityWithJson", method = RequestMethod.POST)
    public City getCity1(@RequestBody City city);
}
