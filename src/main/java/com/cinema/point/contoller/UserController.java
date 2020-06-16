package com.cinema.point.contoller;

import com.cinema.point.contoller.validator.UserValidator;
import com.cinema.point.dto.LoginUserDTO;
import com.cinema.point.dto.RegisterUserDTO;
import com.cinema.point.dto.UserDTO;
import com.cinema.point.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
@Slf4j
public class UserController {

    UserService userService;

    UserValidator userValidator;

    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @InitBinder("registerUser")
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.addValidators(userValidator);
    }

    @GetMapping("/authorization")
    public String get(Model model) {
        LoginUserDTO loginUserDTO = new LoginUserDTO();
        RegisterUserDTO registerUserDTO = new RegisterUserDTO();
        model.addAttribute("loginUser", loginUserDTO);
        model.addAttribute("registerUser", registerUserDTO);
        return "authorization";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerUser") RegisterUserDTO userDTO, BindingResult bindingResult, Model model,
                           HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("loginUser", new LoginUserDTO());
            return "authorization";
        }
        UserDTO findByUsername =
                userService.findByUsername(userDTO.getUsername());
        UserDTO findByEmail = userService.findByEmail(userDTO.getEmail());
        UserDTO findByPhone = userService.findByPhone(userDTO.getPhone());
        log.info("register new user {}", userDTO);
        userService.create(userDTO);
        return "redirect:/authorization";
    }

}
