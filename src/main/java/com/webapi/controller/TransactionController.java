package com.webapi.controller;

import com.webapi.entity.Transaction;
import com.webapi.entity.User;
import com.webapi.repository.TransactionRepository;
import com.webapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping(path = "/transaction")
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    //TO BE MODIFIED
    @PostMapping(path = "/add")
    public @ResponseBody String addTransaction(@RequestParam int userId, @RequestParam int amount, @RequestParam String name) {

        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            Transaction t = new Transaction();
            t.setAmount(amount);
            t.setName(name);
            t.setDate(LocalDateTime.now());
            t.setUser(user);
            transactionRepository.save(t);

            return "Saved";
        } else {
            return "User not found.";
        }
    }
}
