package com.customermanagement.cmbackend.auth.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomerManagementException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public CustomerManagementException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

}
