package com.rory.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.httpBasic()

        http.formLogin()
            .and()
            .authorizeRequests()
             .antMatchers("/v2/api-docs", "/configuration/ui",
              "/swagger-resources", "/configuration/security",
              "/" +
                      "  ", "/webjars/**","/swagger-resources/configuration/ui",
             "/swagge‌​r-ui.html").permitAll()
            .anyRequest()
            .authenticated();
    }
}
