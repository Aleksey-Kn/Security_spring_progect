package ru.example.Authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.example.Authority.servises.CustomHttp403ForbiddenEntryPoint;
import ru.example.Authority.servises.DeniedHandler;
import ru.example.Authority.servises.RestAuthenticationFailureHandler;
import ru.example.Authority.servises.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    CustomHttp403ForbiddenEntryPoint customHttp403ForbiddenEntryPoint;

    @Autowired
    RestAuthenticationFailureHandler failureHandler;

    @Autowired
    DeniedHandler deniedHandler;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/auth/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .anyRequest().permitAll()
                .and()
                //Настройка для входа в систему
                .formLogin()
                .loginPage("/login").failureHandler(failureHandler)
                //Перенарпавление на главную страницу после успешного входа
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/")
                .and().exceptionHandling().authenticationEntryPoint(customHttp403ForbiddenEntryPoint)
                .and().exceptionHandling().accessDeniedHandler(deniedHandler);
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }
}