package com.rory.controller;


import com.rory.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public List<User> query(){
       List<User> list= new ArrayList<User>();
       list.add(new User());
       list.add(new User());
       list.add(new User());
       return list;
    }
}
