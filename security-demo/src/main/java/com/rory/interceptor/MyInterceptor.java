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
   拿到原始的http请求及响应的信息 和真正请求的方法的信息 但是拿不到真正执行的方法的参数的信息
 */
@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        System.out.println(" MyInterceptor preHandle...");
        String className=((HandlerMethod)handler).getBean().getClass().getName();//获取类名
        String methodName=((HandlerMethod)handler).getMethod().getName();
        System.out.println("拦截的类名："+className);
        System.out.println("拦截的方法名："+methodName);
        httpServletRequest.setAttribute("startTime",new Date().getTime());
        if(className.equals("com.rory.controller.UserController")){//只拦截com.rory.controller.UserController类里面的执行方法

            return true;
        }else{
            return true;
        }
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
