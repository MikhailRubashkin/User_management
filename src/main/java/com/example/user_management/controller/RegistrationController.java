package com.example.user_management.controller;

import com.example.user_management.domain.User;
import com.example.user_management.repos.UserRepo;
import com.example.user_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/registration")
    public String registration (){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser ( @Valid User user,
                            BindingResult bindingResult,
                            Model model ){

        if (!userService.addUser (user)) {
            model.addAttribute ("usernameError", "User exists!");
            return "registration";
        }

        return "redirect:/login";
    }
}
