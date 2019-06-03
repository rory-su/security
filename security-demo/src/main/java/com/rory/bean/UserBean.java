package com.rory.bean;

import com.fasterxml.jackson.annotation.JsonView;
import com.rory.model.User;

import javax.validation.constraints.Past;
import java.util.Date;

public class UserBean extends User {

    @Past(message = "生日必须为过去的时间")
    private Date birthday;

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
