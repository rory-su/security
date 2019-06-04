package com.rory.validate.code;


import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = 2669381897614540534L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
