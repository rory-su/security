package com.rory.Aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/*
  执行的顺序 Filter ->Intecptor ->controllerAdvice-> Aspect ->controller
  抛出异常返回的结果为其反序

 可以获取执行的方法 及传递的参数
 但是拿不到http请求的及响应的信息
 */
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
        Object object=pjp.proceed();

        System.out.println("MyAspect  end.......");
        //object 为返回的返回的数据 {"id":"1","username":"sujinquan","birthday":"12132312312"}
        return object;
    }


}
