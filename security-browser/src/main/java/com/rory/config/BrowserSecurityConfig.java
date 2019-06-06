package com.rory.config;

import com.rory.authentication.MyAuthenticationSuccessHandler;
import com.rory.properties.SecurityProperties;
import com.rory.validate.code.ValidateCodeFilter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    public AuthenticationFailureHandler myAuthenticationFailHandler;
    @Autowired
    public SecurityProperties securityProperties;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ValidateCodeFilter validateCodeFilter=new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();
        //http.httpBasic()
         http.addFilterBefore(validateCodeFilter,UsernamePasswordAuthenticationFilter.class)
             .formLogin()
             .loginPage("/authentication/require")
             .loginProcessingUrl("/authentication/form")
             .successHandler(myAuthenticationSuccessHandler) //成功处理器
             .failureHandler(myAuthenticationFailHandler)   //失败处理器
             //.failureForwardUrl("/loginPage.html")
             .and()
             .authorizeRequests()
             .antMatchers("/authentication/require",securityProperties.getBrowser().getLoginPage(), "/code/image" ).permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .csrf().disable();
    }
}
