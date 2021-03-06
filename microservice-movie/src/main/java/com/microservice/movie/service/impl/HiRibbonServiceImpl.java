package com.microservice.movie.service.impl;

import com.microservice.movie.service.HiRibbonService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @program: micro-service
 * @description: 方大佬的ribbon
 * @author: ws
 * @create: 2020-03-30 10:47
 **/
@Service
public class HiRibbonServiceImpl implements HiRibbonService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL_HI = "http://microservice-user/hi?name=weishuai";


    @Override
    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        return restTemplate.getForObject(URL_HI, String.class);
    }

    public String hiError(String name){
        return "hi: " + name + ", sorry, error";
    }
}
