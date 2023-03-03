package com.server.sharemenu.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServiceException {

    public void thrower(int i) throws ArithmeticException, CustomException {
        switch (i) {
            case 1:
                throw new UsernameNotFoundException("Email is not confirmed");
            case 2:
                throw new CustomException("Custom exception");
            case 3:
                throw new CustomException("Invalid Email");
            case 4:
                throw new CustomException("Bad credentials");
        }
    }

}
