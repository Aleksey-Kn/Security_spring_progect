package ru.example.Authority.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.example.Authority.DataBaseAssistant;

@Controller
public class RegisteredController {
    @Autowired
    DataBaseAssistant dataBaseAssistant;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/registration")
    public String registration(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("confirm") String confirm){
        if(!username.equals("admin")
                && confirm.equals(password)
                && dataBaseAssistant.addUser(username, bCryptPasswordEncoder.encode(password))){
            return "index";
        } else return "forRegistration";
    }
}
