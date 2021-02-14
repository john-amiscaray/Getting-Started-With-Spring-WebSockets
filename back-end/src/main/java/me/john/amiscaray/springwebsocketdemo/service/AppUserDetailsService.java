package me.john.amiscaray.springwebsocketdemo.service;

import me.john.amiscaray.springwebsocketdemo.dtos.AppUserDetails;
import me.john.amiscaray.springwebsocketdemo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public AppUserDetailsService(UserService userService){

        this.userService = userService;

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userService.findUserByUsername(s);
        if(user == null){

            throw new UsernameNotFoundException("User does not exist");

        }
        return new AppUserDetails(user);

    }
}
