package com.customermanagement.cmbackend.validator;

import com.customermanagement.cmbackend.user.dto.UserInfoDTO;
import com.customermanagement.cmbackend.utils.error.ErrorCode;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserInfoDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserInfoDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserInfoDTO userInfoDTO = (UserInfoDTO) target;
        validationArguments(errors, "name", userInfoDTO.getName(), "El nombre no puede estar vacío.");
        validationArguments(errors, "lastname", userInfoDTO.getLastname(), "El apellido no puede estar vacío.");
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
