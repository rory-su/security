package com.rory.bean;

import com.rory.model.User;

import java.util.Date;


public class UserBean extends User {

    private Date birthday;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
