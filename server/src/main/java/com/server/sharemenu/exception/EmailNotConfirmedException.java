package com.server.sharemenu.exception;

import org.springframework.security.core.AuthenticationException;

public class EmailNotConfirmedException extends AuthenticationException {
    public EmailNotConfirmedException(String message) {
        super(message);
    }
}
