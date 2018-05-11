package com.tianjunwei.controller;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class HomeController {

	static ThreadPoolExecutor  executor = new ThreadPoolExecutor(1, 1, 200, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(5));
	/*static {
		executor.execute(new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.err.println("hello"+Thread.currentThread().hashCode());
				
			}
		}));
	}*/
	
	@Autowired 
	RestTemplate restTemplate;
	
	String data = "";

    @RequestMapping("start")
    public String start(HttpServletRequest request1,HttpServletResponse response1) throws InterruptedException, IOException {
    	
    	
    	
    	Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				System.err.println(Thread.currentThread().hashCode());
			}
		});
    	executor.execute(thread1);
    	
    	Thread.sleep(1000);
    	
    	Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.err.println(Thread.currentThread().hashCode());
				data = restTemplate.getForObject("http://localhost:9090/foo", String.class);
			}
		});
    	executor.execute(thread);
    	Thread.sleep(10000);
    	
    	return data;
    }
}