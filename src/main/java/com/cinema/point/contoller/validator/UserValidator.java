package com.cinema.point.contoller.validator;


import com.cinema.point.dto.RegisterUserDTO;
import com.cinema.point.dto.UserDTO;
import com.cinema.point.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterUserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegisterUserDTO userDTO = (RegisterUserDTO) o;
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "state.password.not.match");
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
            } else if (userDTO.getFirstName().matches(".*[0-9].*")) {
                errors.rejectValue("firstName", "state.cannot.contain.number");
            }
            if (userDTO.getLastName().equals("")) {
                errors.rejectValue("lastName", "state.required.field");
            } else if (userDTO.getLastName().matches(".*[0-9].*")) {
                errors.rejectValue("lastName", "state.cannot.contain.number");
            }
        }
        UserDTO findByUsername =
                userService.findByUsername(userDTO.getUsername());
        UserDTO findByEmail = userService.findByEmail(userDTO.getEmail());
        UserDTO findByPhone = userService.findByPhone(userDTO.getPhone());
        if (findByUsername != null) {
            errors.rejectValue("username", "state.username.already.use");
        }
        if (findByEmail != null) {
            errors.rejectValue("email", "state.email.already.use");
        }
        if (findByPhone != null) {
            errors.rejectValue("phone", "state.phone.already.use");
        }
    }
}
