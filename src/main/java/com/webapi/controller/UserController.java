package com.webapi.controller;

import com.webapi.entity.Balance;
import com.webapi.entity.User;
import com.webapi.repository.BalanceRepository;
import com.webapi.repository.UserRepository;
import com.webapi.services.LoginRequest;

import com.webapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BalanceRepository balanceRepository;

    @PostMapping("/add")
    public ResponseEntity<User> addNewUser(@RequestBody(required = false) User userRequest) {

        String username = userRequest.getUsername();
        String email = userRequest.getEmail();
        String mobile = userRequest.getMobile();

        if (!userService.findByUsername(username).isPresent() &&
                !userService.findByEmail(email).isPresent() &&
                !userService.findByMobile(mobile).isPresent()) {
            User newUser = new User(
                    username,
                    userRequest.getFirstname(),
                    userRequest.getLastname(),
                    email,
                    mobile,
                    userRequest.getPassword()
            );
            userService.createUser(newUser);

            Balance bal = new Balance();
            bal.setAmount(1000);
            bal.setUser(newUser);
            balanceRepository.save(bal);

            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        if (username != null && password != null) {
            Optional<User> userOptional = userRepository.findByUsernameAndPassword(username, password);

            if (userOptional.isPresent()) {
                return new ResponseEntity<>("Login successful", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<User> getTutorialById(@PathVariable("id") int id) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}