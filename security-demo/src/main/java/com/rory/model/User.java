package com.rory.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.rory.validator.MyConstraint;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;

public class User {

    public interface  UserSimpleView{};
    public interface  UserDetailView extends  UserSimpleView{};

    private Integer id;
    @MyConstraint(message = "这是一个测试的校验器")
    private String username;
    @NotBlank(message ="密码不能为空")
    private String pwdword;

    @JsonView(UserSimpleView.class)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    @JsonView(UserDetailView.class)
    public String getPwdword() {
        return pwdword;
    }
    public void setPwdword(String pwdword) {
        this.pwdword = pwdword == null ? null : pwdword.trim();
    }
}