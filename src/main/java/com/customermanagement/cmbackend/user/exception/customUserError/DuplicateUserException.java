package com.customermanagement.cmbackend.user.exception.customUserError;

public class DuplicateUserException extends Exception{

    public DuplicateUserException(String message, Throwable error) {
        super(message, error);
    }

}
