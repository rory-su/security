package com.rory.serivce.Imp;

import com.rory.serivce.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String greeting(String name) {
        System.out.println("greeting..");
        return "hello "+name;
    }
}
