package com.customermanagement.cmbackend.auth.service;

import com.customermanagement.cmbackend.auth.dto.LoginDTO;

public interface AuthService {
    String login(LoginDTO loginDto);
}
