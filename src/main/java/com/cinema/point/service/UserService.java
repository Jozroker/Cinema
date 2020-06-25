package com.cinema.point.service;

import com.cinema.point.dto.RegisterUserDTO;
import com.cinema.point.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO create(RegisterUserDTO userDTO);

    void deleteById(Long id);

    UserDTO update(RegisterUserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    UserDTO findById(Long id);

    UserDTO findByFirstNameAndLastName(String firstName, String lastName);

    UserDTO findByEmail(String email);

    UserDTO findByPhone(String phone);

    UserDTO findByUsername(String username);

    List<UserDTO> findAll();

    UserDTO findByEmailOrUsername(String emailOrUsername);
}
