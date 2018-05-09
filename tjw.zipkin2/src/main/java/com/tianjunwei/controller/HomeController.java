package com.tianjunwei.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class HomeController {

 
	@Autowired 
	RestTemplate restTemplate;

   
    @RequestMapping("foo")
    public String foo() throws InterruptedException, IOException {
    	
    	String data  = restTemplate.getForObject("http://localhost:9091/bar", String.class);
    	String data2 = restTemplate.getForObject("http://localhost:9092/tar", String.class);
        
    	return data+data2;
    }
}