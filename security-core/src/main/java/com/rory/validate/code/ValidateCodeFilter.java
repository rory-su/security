package com.rory.validate.code;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

    private Logger logger=LoggerFactory.getLogger(getClass());

    private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler ;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        logger.info("ValidateCodeFilter is running here");

        if(StringUtils.equals("/authentication/form",request.getRequestURI())&&StringUtils.endsWithIgnoreCase(request.getMethod(),"post")){
            try{
              validate(new ServletWebRequest(request));
            }catch (ValidateCodeException e){
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
            }

        }
        filterChain.doFilter(request,response);
    }

    private void validate(ServletWebRequest requeset) throws ServletRequestBindingException {
        ImageCode codeInSession=(ImageCode)sessionStrategy.getAttribute(requeset,ValidateCodeController.SESSION_KEY);
        String codeInRequest=ServletRequestUtils.getStringParameter(requeset.getRequest(),"imageCode");
        if(StringUtils.isBlank(codeInRequest)){
            throw new ValidateCodeException("验证码不能为空");
        }
        if(codeInSession==null){
            throw new ValidateCodeException("验证码不存在");
        }
        if(codeInSession.idExpried()){
            sessionStrategy.removeAttribute(requeset,ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码过期");
        }
        if(!StringUtils.equals(codeInSession.getCode(),codeInRequest)){
             throw  new ValidateCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(requeset,ValidateCodeController.SESSION_KEY);
    }

    public SessionStrategy getSessionStrategy() {
        return sessionStrategy;
    }

    public void setSessionStrategy(SessionStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }
}
