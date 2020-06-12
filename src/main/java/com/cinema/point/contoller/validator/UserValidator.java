package com.cinema.point.contoller.validator;


import com.cinema.point.dto.RegisterUserDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterUserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegisterUserDTO userDTO = (RegisterUserDTO) o;
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            errors.rejectValue("passwordConfirm", "password.notmatch");
        }
    }
}
