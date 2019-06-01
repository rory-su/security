package com.rory.validator;

import com.rory.serivce.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyContraintValidator implements ConstraintValidator<MyConstraint, Object> {
    @Autowired
    HelloService helloService;
    @Override
    public void initialize(MyConstraint a) {
       System.out.println("my validator init..");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        helloService.greeting("juan");
        System.out.println("校验的值"+value);
        return false;
    }
}
