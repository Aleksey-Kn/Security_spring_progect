package ru.example.Authority.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.example.Authority.DataBaseAssistant;
import ru.example.Authority.dataManagers.User;
import ru.example.Authority.servises.ValidatorService;

import javax.validation.Valid;

@Validated
@Controller
public class RegisteredController {
    @Autowired
    DataBaseAssistant dataBaseAssistant;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ValidatorService validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @PostMapping("/registration")
    public String registration(@Valid User user, BindingResult bindingResult, @RequestParam("confirm") String confirm){
        if(!bindingResult.hasErrors()
                && !user.getUsername().equals("admin")
                && confirm.equals(user.getPassword())
                && dataBaseAssistant.addUser(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()))){
            return "index";
        } else return "forRegistration";
    }
}
