package ru.example.Authority.servises;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.example.Authority.dataManagers.User;

@Service
public class ValidatorService implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User)o;
        if(user.getUsername().length() < 3){
            errors.rejectValue("username", "short username");
        }
        if(user.getPassword().length() < 3){
            errors.rejectValue("password", "short password");
        }
        if(user.getUsername().contains(" ")){
            errors.rejectValue("username", "contains space");
        }
    }
}
