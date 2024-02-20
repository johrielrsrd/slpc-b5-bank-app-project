package com.webapi.services;

import com.webapi.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserService {

        Optional<User> findByUsername(String username);
        Optional<User> findByEmail(String email);
        Optional<User> findByMobile(String mobile);

        void createUser(User user);

        void createUserWithInitialBalance(User user, int initialBalance);


}

