package com.webapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapi.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByEmailAndPassword(String email, String password);

}