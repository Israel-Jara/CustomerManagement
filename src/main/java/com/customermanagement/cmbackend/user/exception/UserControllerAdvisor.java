package com.customermanagement.cmbackend.user.exception;

import com.customermanagement.cmbackend.user.controller.UserController;
import com.customermanagement.cmbackend.user.exception.customUserError.DuplicateUserException;
import com.customermanagement.cmbackend.user.exception.customUserError.UserNotFoundException;
import com.customermanagement.cmbackend.utils.error.ErrorCode;
import com.customermanagement.cmbackend.utils.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(assignableTypes = {UserController.class})
public class UserControllerAdvisor extends ResponseEntityExceptionHandler {


    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<Object> handleDuplicateUserException(DuplicateUserException duplicateUserException) {
        logger.error(duplicateUserException.getMessage(), duplicateUserException);
        return new ResponseEntity<>(new ErrorResponse(duplicateUserException.getMessage(), ErrorCode.USER_ALREADY_EXISTS), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        logger.error(userNotFoundException.getMessage(), userNotFoundException);
        return new ResponseEntity<>(new ErrorResponse(userNotFoundException.getMessage(), ErrorCode.USER_NOT_FOUND), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleExceptions(Exception exception) {
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(new ErrorResponse("Ha ocurrido un error inesperado. ", ErrorCode.INTERNAL_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
