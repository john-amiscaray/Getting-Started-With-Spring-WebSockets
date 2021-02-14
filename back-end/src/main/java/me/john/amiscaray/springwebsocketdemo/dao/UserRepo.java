package me.john.amiscaray.springwebsocketdemo.dao;

import me.john.amiscaray.springwebsocketdemo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

}
