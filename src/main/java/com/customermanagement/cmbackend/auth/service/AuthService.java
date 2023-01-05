package com.customermanagement.cmbackend.auth.service;

import com.customermanagement.cmbackend.auth.dto.LoginDTO;

public interface AuthService {

    /***
     * Permite la autenticación al sistema.
     * @param loginDto información para ingresar al sismtema.
     * @return el token generado.
     */
    String login(LoginDTO loginDto);
}
