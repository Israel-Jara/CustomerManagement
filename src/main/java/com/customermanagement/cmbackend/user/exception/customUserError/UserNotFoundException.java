package com.customermanagement.cmbackend.user.exception.customUserError;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message, Throwable error) {
        super(message, error);
    }

}
