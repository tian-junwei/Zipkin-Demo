package com.tianjunwei.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import brave.spring.webmvc.SpanCustomizingAsyncHandlerInterceptor;

@Configuration
@EnableWebMvc
public class TracingConfig extends WebMvcConfigurerAdapter {
  @Autowired
  private SpanCustomizingAsyncHandlerInterceptor tracingInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(tracingInterceptor);
  }
}