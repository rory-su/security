package com.rory.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/*
   基于java的反射机制
   对action起作用 因此可以获取拦截的类名和方法名称
   但是不能拦截到http请求的参数对象
 */
@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        System.out.println(" MyInterceptor preHandle...");
        System.out.println("拦截的类名："+((HandlerMethod)handler).getBean().getClass().getName());
        System.out.println("拦截的方法名："+((HandlerMethod)handler).getMethod().getName());
        httpServletRequest.setAttribute("startTime",new Date().getTime());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
      System.out.println("MyInterceptor postHandle..");
      Long startTime=(Long)httpServletRequest.getAttribute("startTime");
      System.out.println("My Interceptor 耗时"+(new Date().getTime()-startTime));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
       System.out.println("MyInterceptor afterCompletion..");
        Long startTime=(Long)httpServletRequest.getAttribute("startTime");
        System.out.println("My Interceptor 耗时"+(new Date().getTime()-startTime));
        System.out.println("ex is"+e);
    }
}
