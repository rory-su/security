package com.rory.config;

import com.rory.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.DispatcherType;
import java.util.ArrayList;
import java.util.List;


@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registFilter(){
        FilterRegistrationBean registraion=new FilterRegistrationBean();
        registraion.setFilter(new MyFilter());

        List<String> urls=new ArrayList<String>();
        urls.add("/user");
        urls.add("/test");
        registraion.setUrlPatterns(urls);

        registraion.addUrlPatterns("/*");
        registraion.addInitParameter("exclusions", "/*.js,/*.gif,/*.jpg,/*.png,/*.css,/*.ico");
        registraion.setDispatcherTypes(DispatcherType.REQUEST);
        registraion.setName("MyFilter");
        registraion.setOrder(1);
        return registraion;
    }
}
