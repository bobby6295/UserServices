package com.demo.utils;

import org.springframework.security.authentication.AuthenticationServiceException;

public class InternalAuthenticationServiceException extends AuthenticationServiceException {
    public InternalAuthenticationServiceException(String msg) {
        super(msg);
    }

    public InternalAuthenticationServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
