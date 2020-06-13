package com.cinema.point.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterUserDTO {

    private Long id;

    @NotEmpty(message = "username value is required")
    private String username;

    @Pattern(regexp = "\\D*", message = "first name cannot contain numbers")
    private String firstName;

    @Pattern(regexp = "\\D*", message = "first name cannot contain numbers")
    private String lastName;

    @NotEmpty(message = "email value is required")
    @Email(message = "invalid mail value")
    private String email;

    @NotEmpty(message = "phone value is required")
    @Pattern(regexp = "^\\d+$", message = "phone cannot contain letters")
    @Size(min = 10, max = 10, message = "invalid phone value")
    private String phone;

    @NotEmpty(message = "password value is required")
    private String password;

    @NotEmpty(message = "confirm password value is required")
    private String confirmPassword;

    private byte[] picture;

}
