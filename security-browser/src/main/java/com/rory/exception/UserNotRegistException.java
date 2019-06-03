package com.rory.exception;

public class UserNotRegistException  extends  RuntimeException{
    private static final long serialVersionUID = -1265349519002762394L;

    public UserNotRegistException(String username){
        this.name=username;
    }
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
