package com.richie.springcloudstarterfeign;

import org.springframework.stereotype.Component;

/**
 * demo
 *
 * Created by lylaut on 2019-05-28
 */
@Component
public class HelloServiceFailure implements HelloService {
    @Override
    public String getHelloContent() {
        return "hello world service is not available !";
    }

    @Override
    public City getCity(String desc) {
        return new City();
    }

    @Override
    public City getCity1(City city) {
        return new City();
    }
}
