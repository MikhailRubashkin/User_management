package com.example.user_management.controller;


import com.example.user_management.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private UserRepo userRepo;


    @GetMapping("/")
    public String greeting ( Model model ){
        String user = SecurityContextHolder.getContext ( ).getAuthentication ( ).getName ( );
        model.addAttribute ("user", user);
        model.addAttribute ("users", userRepo.findAll());
        return "main";
    }
}
