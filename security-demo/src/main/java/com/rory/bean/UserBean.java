package com.rory.bean;

import javax.validation.constraints.Past;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonView;
import com.rory.model.Sys_User;

public class UserBean extends Sys_User {

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
