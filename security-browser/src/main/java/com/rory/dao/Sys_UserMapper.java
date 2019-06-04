package com.rory.dao;

import com.rory.model.Sys_User;

public interface Sys_UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Sys_User record);

    int insertSelective(Sys_User record);

    Sys_User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sys_User record);

    int updateByPrimaryKey(Sys_User record);
}