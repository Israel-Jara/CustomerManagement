package com.customermanagement.cmbackend.user.service;

import com.customermanagement.cmbackend.user.dto.UserDTO;
import com.customermanagement.cmbackend.user.dto.UserInfoDTO;
import com.customermanagement.cmbackend.user.exception.customUserError.DuplicateUserException;
import com.customermanagement.cmbackend.user.exception.customUserError.UserNotFoundException;

import java.util.List;

public interface UserService {

    UserDTO addUser(UserDTO userDTO) throws Exception;

    UserDTO getUser(Integer userId) throws Exception;

    List<UserDTO> getAllUsers();

    UserDTO updateUser(UserInfoDTO userInfoDTO, Integer userId) throws Exception;

    void deleteUser(Integer userId) throws Exception;

}
