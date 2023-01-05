package com.customermanagement.cmbackend.user.service;

import com.customermanagement.cmbackend.user.dto.UserDTO;
import com.customermanagement.cmbackend.user.dto.UserInfoDTO;
import com.customermanagement.cmbackend.user.exception.customUserError.DuplicateUserException;
import com.customermanagement.cmbackend.user.exception.customUserError.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    /***
     * Agrega un usuario al sistema.
     * @param userDTO Tiene los datos necesarios para guardar un usuario.
     * @return El usuario insertado.
     */
    UserDTO addUser(UserDTO userDTO) throws Exception;

    /***
     * Obtiene un usuario a travez de su id.
     * @param userId Id del usuario.
     * @return La información del usuario.
     */
    UserDTO getUser(Integer userId) throws Exception;

    /***
     * Lista los usuarios del sistema.
     * @return Una lista de los usuarios.
     */
    List<UserDTO> getAllUsers();

    /***
     * Actualiza la información del usuario.
     * @param userInfoDTO Información del usuario valida para actualizar.
     * @param userId ID del usuario.
     * @return Información del usuario.
     * @throws Exception
     */
    UserDTO updateUser(UserInfoDTO userInfoDTO, Integer userId) throws Exception;

    /***
     * Elimina un usuario en el sistema.
     * @param userId ID del usuario.
     * @throws Exception
     */
    void deleteUser(Integer userId) throws Exception;

}
