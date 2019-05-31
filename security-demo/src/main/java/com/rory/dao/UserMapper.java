package com.rory.dao;

import com.rory.dto.UserQueryCondition;
import com.rory.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);//新增用户

    User selectByPrimaryKey(Integer id);//根据id查询用户

    int updateByPrimaryKeySelective(User record);//更新用户信息

    int updateByPrimaryKey(User record);//更新用户信息

    User selectByUsername(String username);//根据账号查询用户

    List<User> queryUser(@Param("userQueryCondition")UserQueryCondition userQueryCondition, @Param("pageable") Pageable pageable);//多条件查询
}