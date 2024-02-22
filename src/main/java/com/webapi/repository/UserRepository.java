package com.webapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapi.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
    User findByEmail(String email);

    User findByMobile(String mobile);
}