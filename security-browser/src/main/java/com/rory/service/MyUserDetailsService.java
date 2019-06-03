package com.rory.service;

import com.rory.exception.UserNotRegistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {
    private Logger logger=LoggerFactory.getLogger(getClass());
   @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username)  {
        logger.info("登录用户名：" +username);
        /*
            enable :是否可用 ->用户已失效
            accountNonExpired 账号是否过期
            credentiaNonExpired 密码是否过期 accountNonLocked 账号是否被锁定
         */
        String password=passwordEncoder.encode("123456");
        logger.info("数据库的密码是："+password);
        return (new User(username,password,
                true,true,true,true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin")));
    }
}
