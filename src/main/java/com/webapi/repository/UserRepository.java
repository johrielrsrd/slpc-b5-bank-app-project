package com.webapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapi.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmailAndPassword(String email, String password);

}