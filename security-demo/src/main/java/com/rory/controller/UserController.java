package com.rory.controller;


import com.fasterxml.jackson.annotation.JsonView;
import com.rory.bean.UserBean;
import com.rory.dto.UserQueryCondition;
import com.rory.model.User;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger=LoggerFactory.getLogger(getClass());

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> query(UserQueryCondition userQueryCondition, @PageableDefault(page = 1,size = 5,sort = "username,desc") Pageable pageable){
        String userQueryConditionStr=ReflectionToStringBuilder.toString(userQueryCondition);
        String pageabelStr=ReflectionToStringBuilder.toString(pageable);
        logger.info("用户查询条件"+userQueryConditionStr);
        logger.info("分页查询条件"+pageabelStr);
        List<User> list=new ArrayList<User>();
        list.add(new User());
        list.add(new User());
        list.add(new User());
        return list;
    }
    @GetMapping(value="/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User queryUserById(@PathVariable("id") String id){
        System.out.println("获取用户的id为："+id);
        User user=new User();
        user.setUsername("sujinquan");
        return user;
    }
    @PostMapping
    @JsonView(User.UserSimpleView.class)
    public UserBean createUser(@Valid @RequestBody UserBean user){//, BindingResult errors
//        if(errors.hasErrors()){
//            errors.getAllErrors().forEach(error->System.out.println(error.getDefaultMessage()));
//        }
        String Str=ReflectionToStringBuilder.toString(user);
        System.out.println("接受到的创建用户的信息为："+Str);
        UserBean user_return=new UserBean();
        user_return.setId(1);
        user_return.setUsername("sujinquan");
        user_return.setBirthday(user.getBirthday());
        return user_return;
    }
    @PutMapping("/{id:\\d+}")
    public UserBean updateUser(@Valid @RequestBody UserBean user,BindingResult errors){
        if(errors.hasErrors()){
            errors.getAllErrors().forEach(error->{
                FieldError fieldError=(FieldError)error;
                System.out.println(fieldError.getField()+"  "+error.getDefaultMessage());
            });
        }
        String getUser=ReflectionToStringBuilder.toString(user);
        System.out.println("获取的修改的用户信息："+getUser);
        return user;
    }
    @DeleteMapping("{id:\\d+}")
    public void deleteUser(@PathVariable String id){
     System.out.println("删除用户的id"+id);
    }



}
