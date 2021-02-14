package me.john.amiscaray.springwebsocketdemo.service;

import me.john.amiscaray.springwebsocketdemo.dao.UserRepo;
import me.john.amiscaray.springwebsocketdemo.dtos.UserDto;
import me.john.amiscaray.springwebsocketdemo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder){

        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;

    }

    public User findUserByUsername(String username){

        return userRepo.findUserByUsername(username);

    }

    public void saveUser(UserDto dto){

        userRepo.save(new User(dto, passwordEncoder));

    }

}
