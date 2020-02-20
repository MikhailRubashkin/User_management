package com.example.user_management.controller;


import com.example.user_management.domain.User;
import com.example.user_management.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

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

    @PostMapping("filter")
    public String filter ( @RequestParam String filter, Map<String, Object> model) {
        UserControllerFilterMethod (filter, model);
        return "main";
    }

    private void UserControllerFilterMethod ( @RequestParam String filter, Map<String, Object> model ) {
        String user = SecurityContextHolder.getContext ( ).getAuthentication ( ).getName ( );
        Iterable<User> users;
        if (filter != null && !filter.isEmpty ( )) {
            users = Collections.singleton (userRepo.findByUsername (filter));
        } else {
            users = userRepo.findAll ( );
        }
        model.put ("users", users);
        model.put ("user", user);
    }
}
