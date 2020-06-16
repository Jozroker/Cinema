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
            errors.rejectValue("confirmPassword", "state.not.match");
        }
        if (userDTO.getPhone().equals("")) {
            errors.rejectValue("phone", "state.required.field");
        } else if (userDTO.getPhone().length() != 10) {
            errors.rejectValue("phone", "state.phone.length");
        } else if (userDTO.getPhone().matches(".*[a-zA-Zа-яА-Я].*")) {
            errors.rejectValue("phone", "state.phone.letters");
        }
        if (!userDTO.getFirstName().equals("") || !userDTO.getLastName().equals("")) {
            if (userDTO.getFirstName().equals("")) {
                errors.rejectValue("firstName", "state.required.field");
            }
            if (userDTO.getLastName().equals("")) {
                errors.rejectValue("lastName", "state.required.field");
            }
        }
    }
}
