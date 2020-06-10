package com.cinema.point.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class UserDTO {

    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    @Pattern(regexp = "\\D*")
    private String firstName;

    @NotEmpty
    @Pattern(regexp = "\\D*")
    private String lastName;

    @NotEmpty
    @Email(message = "invalid mail value")
    private String email;

    @NotEmpty
    @Size(min = 10, max = 10, message = "invalid phone value")
    private String phone;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

//    private List<TicketDTO> tickets;
private List<Long> ticketsId;
}
