package com.cinema.point.service.impl;

import com.cinema.point.domain.Ticket;
import com.cinema.point.domain.User;
import com.cinema.point.dto.RegisterUserDTO;
import com.cinema.point.dto.UserDTO;
import com.cinema.point.errors.ResourceNotFoundException;
import com.cinema.point.repository.TicketRepository;
import com.cinema.point.repository.UserRepository;
import com.cinema.point.service.UserService;
import com.cinema.point.service.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    TicketRepository ticketRepository;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, TicketRepository ticketRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public UserDTO create(RegisterUserDTO userDTO) {
        log.debug("creating new user {}", userDTO);
        User user = userMapper.toEntity(userDTO);
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public void deleteById(Long id) {
        log.debug("deleting user by id {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO update(RegisterUserDTO userDTO) {
        log.debug("updating user {}", userDTO);
        User user = userMapper.toEntity(userDTO);
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        log.debug("updating user {}", userDTO);
        User user = userMapper.toEntity(userDTO);
        List<Ticket> tickets =
                userDTO.getTicketsId().stream().map(id -> ticketRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Ticket", id)))
                        .collect(Collectors.toList());
        user.setTickets(tickets);
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserDTO findById(Long id) {
        log.debug("finding user by id {}", id);
        return userMapper.toDTO(userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id)));
    }

    @Override
    public UserDTO findByFirstNameAndLastName(String firstName, String lastName) {
        log.debug("finding user by first name {} and last name {}", firstName, lastName);
        return userMapper.toDTO(userRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new ResourceNotFoundException("User",
                        "(first name, last name) " + firstName + ", " + lastName)));
    }

    @Override
    public UserDTO findByEmail(String email) {
        log.debug("finding user by email {}", email);
        return userMapper.toDTO(userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User",
                        "(email) " + email)));
    }

//    @Override
//    public LoginUserDTO findByEmailLogin(String email) {
//        log.debug("finding user by email {}: to user login dto", email);
//        return userMapper.toDTO(userRepository.findByEmail(email)
//                .orElseThrow(() -> new ResourceNotFoundException("User",
//                        "(email) " + email)));
//    }

    @Override
    public UserDTO findByPhone(String phone) {
        log.debug("finding user by phone {}", phone);
        return userMapper.toDTO(userRepository.findByPhone(phone)
                .orElseThrow(() -> new ResourceNotFoundException("User",
                        "(phone) " + phone)));
    }

    @Override
    public UserDTO findByUsername(String username) {
        log.debug("finding user by username {}", username);
        return userMapper.toDTO(userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User",
                        "(username) " + username)));
    }

//    @Override
//    public LoginUserDTO findByUsernameLogin(String username) {
//        return null;
//    }

    @Override
    public List<UserDTO> findAll() {
        log.debug("finding all users");
        return userRepository.findAll().stream()
                .map(userMapper::toDTO).collect(Collectors.toList());
    }
}
