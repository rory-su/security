package com.rory.Aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
    //切入点 定义什么时候起作用
    // @Before()  方法执行之前起作用
    // @After()   方法执行之后起作用
    // @AfterThrowing  方法抛出异常的时候起作用
    // @Around()  方法执行之前和之后都起作用  覆盖了前面三种方法

    @Around("execution(* com.rory.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        //ProceedingJoinPoint 包含了拦截的方法的信息
     System.out.println("MyAspect  start.....");
     Object[] args=pjp.getArgs();
        for (Object arg:args) {
            System.out.println("参数是："+arg);
        }
     pjp.proceed();
     System.out.println("MyAspect  end.......");
        return null;
    }

    @Around("execution(* com.rory.DateUtil.*(..))")
    public Object handleTestMethod(ProceedingJoinPoint pjp) throws Throwable {
        //ProceedingJoinPoint 包含了拦截的方法的信息
        System.out.println("MyAspect  start.....");
        Object[] args=pjp.getArgs();
        for (Object arg:args) {
            System.out.println("参数是："+arg);
        }
        pjp.proceed();
        System.out.println("MyAspect  end.......");
        return null;
    }





}
