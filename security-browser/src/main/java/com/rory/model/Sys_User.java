package com.rory.model;

import com.fasterxml.jackson.annotation.JsonView;

public class Sys_User {

    public interface  UserSimpleView{};
    public interface  UserDetailView extends  UserSimpleView{};

    private Integer id;
    private String username;
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