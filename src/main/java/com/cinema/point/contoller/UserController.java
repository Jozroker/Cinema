package com.cinema.point.contoller;

import com.cinema.point.dto.LoginUserDTO;
import com.cinema.point.dto.RegisterUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Slf4j
public class UserController {

    @GetMapping("/authorization")
    public String get(Model model) {
        LoginUserDTO loginUserDTO = new LoginUserDTO();
        RegisterUserDTO registerUserDTO = new RegisterUserDTO();
        model.addAttribute("loginUser", loginUserDTO);
        model.addAttribute("registerUser", registerUserDTO);
        return "login";
    }
}
