package ru.example.Authority.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.example.Authority.DataBaseAssistant;
import ru.example.Authority.dataManagers.Role;
import ru.example.Authority.dataManagers.User;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    DataBaseAssistant dataBaseAssistant;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.equals("admin")){
            return new User(bCryptPasswordEncoder.encode("owner"), "admin",
                    List.of(new Role("ROLE_ADMIN"), new Role("ROLE_USER")));
        } else return dataBaseAssistant.getUser(username);
    }
}