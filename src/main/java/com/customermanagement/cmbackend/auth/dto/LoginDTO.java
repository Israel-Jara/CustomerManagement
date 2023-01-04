package com.customermanagement.cmbackend.auth.dto;

import lombok.Data;

@Data
public class LoginDTO {

    private String usernameOrEmail;
    private String password;

}
