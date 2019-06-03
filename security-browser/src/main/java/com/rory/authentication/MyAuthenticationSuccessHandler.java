package com.rory.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rory.properties.LoginType;
import com.rory.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
    成功处理器实现方式
    方式一：实现 implements AuthenticationSuccessHandler接口
    方式二：继承SimpleUrlAuthenticationSuccessHandler 为默认的成功处理器
 */
@Component
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private Logger logger=LoggerFactory.getLogger(getClass());
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SecurityProperties securityProperties;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功"+securityProperties.getBrowser().getLoginType());
        if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
            //{"authorities":[{"authority":"admin"}],
            // "details":{"remoteAddress":"0:0:0:0:0:0:0:1","sessionId":"50194C26F1370FEC30AD47B5971559E8"},
            // "authenticated":true,"principal":{"password":null,"username":"rory","authorities":[{"authority":"admin"}],
            // "accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true,"enabled":true},
            // "credentials":null,"name":"rory"}
        }else{
            super.onAuthenticationSuccess(request,response,authentication);
        }


    }
}
