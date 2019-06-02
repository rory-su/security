package com.rory.controller;


import com.rory.DateUtil;
import com.rory.exception.UserNotExistException;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public String test(HttpServletResponse response, HttpServletRequest request){
       //String str = ReflectionToStringBuilder.toString(request);
       // throw  new UserNotExistException("123");
        //JSONArray array = JSONArray.fromObject(list);
       // System.out.print(str);\
        String str= DateUtil.getNow();
        System.out.println(str);
        return str;
    }
}
