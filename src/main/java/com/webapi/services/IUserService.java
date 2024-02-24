package com.webapi.services;

import com.webapi.entity.User;

import java.util.Optional;

public interface IUserService {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByMobile(String mobile);

    void createUserWithInitialBalance(User user, int initialBalance);


}

