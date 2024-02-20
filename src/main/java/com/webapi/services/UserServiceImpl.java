package com.webapi.services;

import com.webapi.entity.User;
import com.webapi.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    //    public Optional<User> findByUsernameEmailMobile(String username, String email, String mobile){
//        return Optional.ofNullable(userRepository.findByUsernameEmailMobile(username, email, mobile));
//    }
    public void createUser(User user){userRepository.save(user);}

    public Optional<User> findByUsername(String username){
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    public Optional<User> findByEmail(String email){
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public Optional<User> findByMobile(String mobile){
        return Optional.ofNullable(userRepository.findByMobile(mobile));
    }

    /*@Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByMobile(String mobile) {
        return Optional.empty();
    }

    @Override
    public void createUser(User user) {

    }*/
}
