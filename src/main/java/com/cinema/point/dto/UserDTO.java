package com.cinema.point.dto;

import com.cinema.point.domain.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class UserDTO {

    private Long id;

    @NotEmpty(message = "username value is required")
    private String username;

    @NotEmpty(message = "first name value is required")
    @Pattern(regexp = "\\D*")
    private String firstName;

    @NotEmpty(message = "last name value  is required")
    @Pattern(regexp = "\\D*")
    private String lastName;

    @NotEmpty(message = "email value is required")
    @Email(message = "invalid mail value")
    private String email;

    @NotEmpty(message = "phone value is required")
    @Size(min = 10, max = 10, message = "invalid phone value")
    private String phone;

    @NotEmpty(message = "password value is required")
    private String password;

    @NotEmpty(message = "role is empty")
    private Role role;

    private byte[] picture;

    //    private List<TicketDTO> tickets;
    private List<Long> ticketsId;
}
