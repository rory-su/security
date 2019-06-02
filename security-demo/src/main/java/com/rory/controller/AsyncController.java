package com.rory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController

public class AsyncController {
    private Logger logger= LoggerFactory.getLogger(getClass());

    @RequestMapping("/order")
    public String order() throws InterruptedException {
        logger.info("主线程开始");
        Thread.sleep(5000);
        logger.info("主线程返回");
        return "success";
    }

    /*
     使用Callable开始新线程
     副线程是写在主线程里面的
     */
    @RequestMapping("order_async")
    public Callable<String> orderAsync(){
        logger.info("主线程开始");
        Callable<String> result=new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("副线程开始");
                Thread.sleep(1000);
                logger.info("副线程返回");
                return "success";
            }
        };
        logger.info("主线程返回");
         return result;
    }
}
