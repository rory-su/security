package com.rory.controller;

import com.rory.exception.UserNotExistException;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
       // throw  new UserNotExistException("123");
        return "testee";
    }
}
