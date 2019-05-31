package com.rory.controller;

import com.rory.exception.UserNotExistException;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/test")
    public String test(){
        throw  new UserNotExistException("123");
//    return "test";
    }
}
