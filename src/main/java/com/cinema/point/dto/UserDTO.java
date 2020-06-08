package com.cinema.point.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class UserDTO {

    private Long id;

    @NotNull(message = "required field")
    private String username;

    @NotNull(message = "required field")
    @Pattern(regexp = "\\D*")
    private String firstName;

    @NotNull(message = "required field")
    @Pattern(regexp = "\\D*")
    private String lastName;

    @NotNull(message = "required field")
    @Email(message = "invalid mail value")
    private String email;

    @NotNull(message = "required field")
    @Size(min = 10, max = 10, message = "invalid phone value")
    private String phone;

    @NotNull(message = "required field")
    private String password;

    @NotNull(message = "required field")
    private String confirmPassword;

    private List<TicketDTO> tickets;
}
