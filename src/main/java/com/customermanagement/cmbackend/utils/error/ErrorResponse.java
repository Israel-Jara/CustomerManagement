package com.customermanagement.cmbackend.utils.error;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ErrorResponse {

    public ErrorCode errorCode;
    public String message;
    public Integer count;

    private List<String> errors;

    public ErrorResponse(String message, ErrorCode errorCode) {
        this.message = message;
        this.errorCode = errorCode;
        this.count = 1;
        this.errors = new ArrayList<>();
    }
}
