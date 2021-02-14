package me.john.amiscaray.springwebsocketdemo.entities;

import me.john.amiscaray.springwebsocketdemo.dtos.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String authority;

    public User(){



    }

    public User(UserDto dto, PasswordEncoder encoder){

        username = dto.getUsername();
        password = encoder.encode(dto.getPassword());
        authority = "ROLE_USER";

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
