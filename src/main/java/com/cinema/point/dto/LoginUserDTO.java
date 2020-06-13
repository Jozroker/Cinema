package com.cinema.point.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginUserDTO {

    private Long id;

    @NotEmpty(message = "value is required")
    private String usernameOrEmail;

//    @NotEmpty(message = "email value is required")
////    @Email(message = "invalid mail value")
//    private String email;

    @NotEmpty(message = "password value is required")
    private String pass;

}
