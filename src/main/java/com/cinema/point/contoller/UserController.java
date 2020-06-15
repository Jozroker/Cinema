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

    //todo check password is bcrypted

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginUser") LoginUserDTO userDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("registerUser", new RegisterUserDTO());
            return "authorization";
        }
        log.info("sign in user {}", userDTO);
        UserDTO result1 = userService.findByEmail(userDTO.getUsernameOrEmail());
        UserDTO result2 = userService.findByUsername(userDTO.getUsernameOrEmail());
        UserDTO user = null;
        if (result1 == null && result2 == null) {
            model.addAttribute("registerUser", new RegisterUserDTO());
            return "authorization";
        } else {
            user = result1 == null ? result2 : result1;
        }
        model.addAttribute("user", user);
        return "redirect:/home";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registerUser") RegisterUserDTO userDTO, BindingResult bindingResult, Model model,
                           HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("loginUser", new LoginUserDTO());
            return "authorization";
        }
        log.info("register new user {}", userDTO);
        userService.create(userDTO);
//        UserDTO user = userService.findByPhone(userDTO.getPhone());
//        model.addAttribute("user", user);
        return "redirect:/authorization";
    }
}
