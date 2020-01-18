package com.example.demo;

import com.example.demo.comm.interceptor.HeaderParamsCheckInterceptor;
import com.example.demo.comm.interceptor.ResponseResultInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private ResponseResultInterceptor responseResultInterceptor;
    @Autowired
    private HeaderParamsCheckInterceptor headerParamsCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String apiUri = "/**";
        //请求头参数校验
        registry.addInterceptor(headerParamsCheckInterceptor).addPathPatterns(apiUri);
        //响应结果控制拦截
        registry.addInterceptor(responseResultInterceptor).addPathPatterns(apiUri);
    }



}
