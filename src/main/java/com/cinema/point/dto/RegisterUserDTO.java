package com.cinema.point.dto;

import com.cinema.point.domain.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class RegisterUserDTO {

    private Long id;

    @NotEmpty(message = "state.required.field")
    private String username;

    @Pattern(regexp = ".*[0-9].*", message = "state.cannot.contain.number")
    private String firstName;

    @Pattern(regexp = ".*[0-9].*", message = "state.cannot.contain.number")
    private String lastName;

    @NotEmpty(message = "state.required.field")
    @Email(message = "state.email.invalid")
    private String email;

    //    @NotEmpty(message = "phone value is required")
//    @Pattern(regexp = "^\\d+$", message = "phone cannot contain letters")
//    @Size(min = 10, max = 10, message = "invalid phone value")
    private String phone;

    @NotEmpty(message = "state.required.field")
    private String password;

    @NotEmpty(message = "state.required.field")
    private String confirmPassword;

    private Role role = Role.USER;

    private byte[] picture;

}
