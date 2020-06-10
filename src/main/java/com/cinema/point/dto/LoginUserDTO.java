package com.cinema.point.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class LoginUserDTO {

    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    @Email(message = "invalid mail value")
    private String email;

    @NotEmpty
    private String password;

}
