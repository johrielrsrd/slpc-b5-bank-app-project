package com.webapi.services;

import com.webapi.entity.Balance;
import com.webapi.entity.User;
import com.webapi.repository.BalanceRepository;
import com.webapi.repository.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BalanceRepository balanceRepository;

    public void createUserWithInitialBalance(User user, int initialBalance) {
        userRepository.save(user);

        Balance balance = new Balance();
        balance.setAmount(initialBalance);
        balance.setUser(user);
        balanceRepository.save(balance);
    }

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public Optional<User> findByMobile(String mobile) {
        return Optional.ofNullable(userRepository.findByMobile(mobile));
    }
}
