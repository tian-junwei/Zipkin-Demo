package com.tianjunwei.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class HomeController {

	@Autowired 
	RestTemplate restTemplate;

    @RequestMapping("start")
    public String start(HttpServletRequest request1,HttpServletResponse response1) throws InterruptedException, IOException {
        
    	String data  = restTemplate.getForObject("http://localhost:9090/foo", String.class);
    	
    	return data;
    }
}