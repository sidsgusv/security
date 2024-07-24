package org.example.security.config;



import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {


@Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
       return httpSecurity
                .authorizeHttpRequests(
                        authorizeRequests ->
                                authorizeRequests
                                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()

                                        .requestMatchers("/","/users/login","/users/register").permitAll()
                                        .anyRequest().authenticated()
                )
                .formLogin(formLogin->
                        formLogin.loginPage("/users/login")
                                .usernameParameter("username"))
               .logout(
                       logout->
                               logout
                                       .logoutUrl("/users/logout")
                                       .logoutSuccessUrl("/")
                                       .invalidateHttpSession(true)

               )

                .build();



    }

    @Bean
    public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
    }





}

