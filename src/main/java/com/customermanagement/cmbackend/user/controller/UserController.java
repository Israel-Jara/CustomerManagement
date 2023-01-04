package com.customermanagement.cmbackend.user.controller;

import com.customermanagement.cmbackend.user.dto.UserDTO;
import com.customermanagement.cmbackend.user.dto.UserInfoDTO;
import com.customermanagement.cmbackend.user.service.UserService;
import com.customermanagement.cmbackend.utils.error.ErrorCode;
import com.customermanagement.cmbackend.utils.error.ErrorResponse;
import com.customermanagement.cmbackend.validator.UserDTOValidator;
import com.customermanagement.cmbackend.validator.UserInfoDTOValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserDTOValidator userDTOValidator;
    private final UserInfoDTOValidator userInfoDTOValidator;

    public UserController(UserService userService, UserDTOValidator userDTOValidator, UserInfoDTOValidator userInfoDTOValidator) {
        this.userService = userService;
        this.userDTOValidator = userDTOValidator;
        this.userInfoDTOValidator = userInfoDTOValidator;
    }

    @PostMapping("/add-user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDTO newUserDTO, BindingResult result) throws Exception {

        if (result.hasErrors()) {
            ErrorResponse errorResponse = createErrorResponse(result);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        UserDTO userDTO = userService.addUser(newUserDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }


    @GetMapping(value = {"/{userId}"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer userId) throws Exception {
        UserDTO userDTO = userService.getUser(userId);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> userDTOS = userService.getAllUsers();
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @PutMapping("{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateUser(@PathVariable Integer userId, @Valid @RequestBody UserInfoDTO userInfoDTO, BindingResult result) throws Exception {

        if (result.hasErrors()) {
            ErrorResponse errorResponse = createErrorResponse(result);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        UserDTO updatedUserDTO = userService.updateUser(userInfoDTO, userId);
        return new ResponseEntity<>(updatedUserDTO, HttpStatus.OK);
    }


    @DeleteMapping("{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) throws Exception {
        userService.deleteUser(userId);
        return new ResponseEntity<>("Usuario eliminado.", HttpStatus.OK);
    }

    private ErrorResponse createErrorResponse(BindingResult result) {
        List<ObjectError> allErrors = result.getAllErrors();
        List<String> errors = new ArrayList<>();
        allErrors.forEach(objectError -> errors.add(objectError.getDefaultMessage()));

        String messageError = errors.size() < 2 ? "Error de validación." : "Errores de validación.";

        ErrorResponse errorResponse = new ErrorResponse(messageError, ErrorCode.ILLEGAL_ARGUMENT);
        errorResponse.setCount(result.getErrorCount());
        errorResponse.setErrors(errors);
        /*errors.forEach((error) -> {
            logger.error("Error on userSectionInfoDTOValidator: " + error);
        });*/
        return errorResponse;
    }

    @InitBinder("userDTO")
    public void initUserInfoCreationDTOValidator(WebDataBinder dataBinder) {
        dataBinder.setValidator(userDTOValidator);
    }

    @InitBinder("userInfoDTO")
    public void initUserInfoUpdateDTOValidator(WebDataBinder dataBinder) {
        dataBinder.setValidator(userInfoDTOValidator);
    }

}
