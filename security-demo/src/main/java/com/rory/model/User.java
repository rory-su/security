package com.rory.model;

public class User {
    private Integer id;

    private String username;

    private String pwdword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPwdword() {
        return pwdword;
    }

    public void setPwdword(String pwdword) {
        this.pwdword = pwdword == null ? null : pwdword.trim();
    }
}