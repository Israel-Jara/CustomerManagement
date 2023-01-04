package com.customermanagement.cmbackend.validator;

import com.customermanagement.cmbackend.user.dto.UserDTO;
import com.customermanagement.cmbackend.utils.error.ErrorCode;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDTO = (UserDTO) target;
        validationArguments(errors, "name", userDTO.getName(), "El nombre no puede estar vacío.");
        validationArguments(errors, "lastname", userDTO.getLastname(), "El apellido no puede estar vacío.");
        validationArguments(errors, "email", userDTO.getEmail(), "El email no puede estar vacío.");
    }

    private void validationArguments(Errors errors,String field, String argument, String message) {

        if (argument == null) {
            errors.rejectValue(field, String.valueOf(ErrorCode.ILLEGAL_ARGUMENT), message);
        }
        else if (argument.isBlank() || argument.isEmpty())
        {
            errors.rejectValue(field, String.valueOf(ErrorCode.ILLEGAL_ARGUMENT), message);
        }
    }

}
