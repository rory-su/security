package com.rory.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rory.bean.SimpleResponse;
import com.rory.properties.LoginType;
import com.rory.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
   失败处理器
   方式一：实现implements AuthenticationFailureHandler接口
   方式二：继承SimpleUrlAuthenticationFailureHandler 其为默认的接口
 */
@Component
public class MyAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {
    private Logger logger=LoggerFactory.getLogger(getClass());
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
     logger.info("登录失败"+securityProperties.getBrowser().getLoginType());
     if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
         response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
         response.setContentType("application/json;charset=UTF-8");
         //response.getWriter().write(objectMapper.writeValueAsString(e));//包含栈的错误信息
         response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(e.getMessage())));
     }else{
         super.onAuthenticationFailure(request,response,e);
     }


    }
}
