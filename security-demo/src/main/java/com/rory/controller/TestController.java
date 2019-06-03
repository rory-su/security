package com.rory.controller;


import com.rory.DateUtil;
import com.rory.bean.FileInfo;
import com.rory.exception.UserNotRegistException;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@Controller
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public String test(HttpServletResponse response, HttpServletRequest request){
       //String str = ReflectionToStringBuilder.toString(request);
        //throw  new UserNotExistException("123");
        throw new UserNotRegistException("rory");
        //JSONArray array = JSONArray.fromObject(list);
       // System.out.print(str);\
        //String str= DateUtil.getNow();
       // System.out.println(str);
       // System.out.println("hello world");
        //return str;
    }






}
