package com.rory.bean;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SimpleResponse {

    public SimpleResponse(Object content){
        this.content=content;
    }

    private Object content;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }


}
