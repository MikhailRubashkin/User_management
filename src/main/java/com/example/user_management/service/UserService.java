package com.example.user_management.service;

import com.example.user_management.domain.Role;
import com.example.user_management.domain.User;
import com.example.user_management.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService implements UserDetailsService {

    private static Logger log = Logger.getLogger("UserService");

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }



    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public boolean addUser( User user) {
        log.info("Saving user=" + user);
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }
        String regex = "[а-яёА-ЯЁ]+";
        String str = user.toString ( );
        Pattern pattern = Pattern.compile (regex);
        Matcher m = pattern.matcher (str);
         if (m.find ( )) {
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Encoded password: " + passwordEncoder);
        userRepo.save(user);

        return true;
    }

}

