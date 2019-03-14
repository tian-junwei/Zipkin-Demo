package com.tianjunwei.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
    public String start(HttpServletRequest request1,HttpServletResponse response1) throws Exception, IOException {

    	logger.info("start");
    	db();

    	String data = restTemplate.getForObject("http://localhost:9090/foo", String.class);

    	return data;
    }

    public void db() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("成功加载驱动");

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			String url = "jdbc:mysql://localhost:3306/tian?user=root&password=root&useUnicode=true&characterEncoding=UTF8&statementInterceptors=brave.mysql.TracingStatementInterceptor&zipkinServiceName=myDatabaseService";
			connection = DriverManager.getConnection(url);
			System.out.println("成功获取连接");

			statement = connection.createStatement();
			String sql = "select * from tbl_user";
			resultSet = statement.executeQuery(sql);

			resultSet.beforeFirst();
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1));
			}
			System.out.println("成功操作数据库");
		} catch(Throwable t) {
			// TODO 处理异常
			t.printStackTrace();
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
			System.out.println("成功关闭资源");
		}

	}

}