package ru.example.Authority.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.example.Authority.DataBaseAssistant;

@RestController
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
            return "Successful";
        } else return "Error";
    }
}
