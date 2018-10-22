package com.tianjunwei.controller;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import brave.Tracing;
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

	@Autowired
	Tracing tracing;
	


    @RequestMapping("start")
    public String start(HttpServletRequest request1,HttpServletResponse response1) throws InterruptedException, IOException {

    	logger.info("start");

    	String data = restTemplate.getForObject("http://localhost:9090/foo", String.class);

    	return data;
    }
}