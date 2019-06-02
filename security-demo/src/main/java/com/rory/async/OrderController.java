package com.rory.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/*
  使用DeferredResult创建副线程
 */

@RestController
public class OrderController {

    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    MockQueue mockQueue;
    @Autowired
    DeferredResultHolder deferredResultHolder;

    @RequestMapping("/order_result")
    public DeferredResult<String> order_async() throws InterruptedException {
     logger.info("主线程开始");
     String orderNumber= RandomStringUtils.randomNumeric(8);
     mockQueue.setPlaceOrder(orderNumber);
     DeferredResult<String> result=new DeferredResult<String>();//使用DeferredResult创建副线程
     deferredResultHolder.getMap().put(orderNumber,result);
     logger.info("主线程返回");
        return result;
    }
}
