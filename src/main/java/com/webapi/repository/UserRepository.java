package com.webapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapi.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}