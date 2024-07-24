package org.example.security.service;

import jakarta.annotation.PostConstruct;
import org.example.security.model.entity.UserEntity;
import org.example.security.model.entity.UserRoleEntity;

import org.example.security.model.enums.UserRoleEnum;
import org.example.security.repository.UserRepository;
import org.example.security.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public InitService(UserRepository userRepository,
                       UserRoleRepository userRoleRepository,
                       PasswordEncoder passwordEncoder,
                       @Value("${app.default.password}")String defaultPassword) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostConstruct
    public void init() {
        initRoles();
        initUsers();
    }


    private void initRoles() {
        if (userRoleRepository.count() == 0) {

//            var moderatorRole=new UserRoleEntity().setRole(UserRoleEnum.MODERATOR);
//            var adminRole=new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
//            userRoleRepository.save(moderatorRole);
//            userRoleRepository.save(adminRole);
    UserRoleEntity adminRole = userRoleRepository.save(new UserRoleEntity().setRole(UserRoleEnum.ADMIN));
    UserRoleEntity moderatorRole = userRoleRepository.save(new UserRoleEntity().setRole(UserRoleEnum.MODERATOR));

        }

    }

    private void initUsers(){
        if (userRepository.count()==0){
            initAdmin();
            initModerator();
            initNormalUser();

        }
    }

    private void initAdmin(){
        UserEntity adminUser = new UserEntity()
                .setEmail("admin@example.com")
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setPassword(passwordEncoder.encode("topsecret"))
                        .setRoles(userRoleRepository.findAll());

        userRepository.save(adminUser);



    }
    private void initModerator(){
        UserEntity moderatorUser = new UserEntity()
                .setEmail("moderator@example.com")
                .setFirstName("Moto")
                .setLastName("Motev")
                .setPassword(passwordEncoder.encode("topsecret"))
                .setRoles(List.of(userRoleRepository.findUserRoleEntityByRole(UserRoleEnum.MODERATOR).orElseThrow()));

        userRepository.save(moderatorUser);



    }
    private void initNormalUser(){
        UserEntity normalUser = new UserEntity()
                .setEmail("user@example.com")
                .setFirstName("Pesho")
                .setLastName("Peshev")
                .setPassword(passwordEncoder.encode("topsecret"));


        userRepository.save(normalUser);



    }
}
