package com.tianjunwei.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class HomeController {

	Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired 
	RestTemplate restTemplate;

   
    @RequestMapping("foo")
    public String foo() throws InterruptedException, IOException {
    	
    	String data  = restTemplate.getForObject("http://localhost:9091/bar", String.class);
    	String data2 = restTemplate.getForObject("http://localhost:9092/tar", String.class);
        logger.info("foo");

    	return data+data2;
    }
}