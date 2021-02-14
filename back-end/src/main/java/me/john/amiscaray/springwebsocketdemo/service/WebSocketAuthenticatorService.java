package me.john.amiscaray.springwebsocketdemo.service;

import me.john.amiscaray.springwebsocketdemo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class WebSocketAuthenticatorService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    public UsernamePasswordAuthenticationToken getAuthenticatedOrFail(String username, String password) throws AuthenticationException {

        // Check the username and password are not empty
        if (username == null || username.trim().isEmpty()) {
            throw new AuthenticationCredentialsNotFoundException("Username was null or empty.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new AuthenticationCredentialsNotFoundException("Password was null or empty.");
        }
        // Check that the user with that username exists
        User user = userService.findUserByUsername(username);
        if(user == null){
            throw new AuthenticationCredentialsNotFoundException("User not found");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                username,
                password,
                Collections.singletonList(new SimpleGrantedAuthority(user.getAuthority()))

        );

        // verify that the credentials are valid
        authManager.authenticate(token);

        // Erase the password in the token after verifying it because we will pass it to the STOMP headers.
        token.eraseCredentials();

        return token;
    }
}