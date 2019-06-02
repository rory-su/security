package com.rory.config;

import com.rory.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResultProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private MyInterceptor myInterceptor;


    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
       // super.configureAsyncSupport(configurer);

        //拦截异步请求
       // configurer.registerCallableInterceptors();
        //configurer.registerDeferredResultInterceptors((DeferredResultProcessingInterceptor) myInterceptor);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor);

        //添加多个拦截器
        //registry.addInterceptor(otherintecptor);
        // super.addInterceptors(registry);

    }




}
