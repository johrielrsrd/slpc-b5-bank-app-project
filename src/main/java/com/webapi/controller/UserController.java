package com.webapi.controller;

import com.webapi.entity.User;
import com.webapi.repository.BalanceRepository;
import com.webapi.repository.UserRepository;
import com.webapi.DTO.LoginRequest;

import com.webapi.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private IUserService IUserService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BalanceRepository balanceRepository;

    @CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping("/add")
    public ResponseEntity<User> addNewUser(@RequestBody(required = false) User newUser) {

        String username = newUser.getUsername();
        String email = newUser.getEmail();
        String mobile = newUser.getMobile();

        if (!IUserService.findByUsername(username).isPresent() &&
                !IUserService.findByEmail(email).isPresent() &&
                !IUserService.findByMobile(mobile).isPresent()) {

            IUserService.createUserWithInitialBalance(newUser, 1000);

            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.username();
        String password = loginRequest.password();

        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
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
