package com.formpractice.springboot.service;

import com.formpractice.springboot.model.UserModel;
import com.formpractice.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserModel registerUser(String username, String password, String email){
        if (username == null || password == null) {
            return null;
        } else {
            if(userRepository.findByUsername(username).isPresent()){
                System.out.println("Username already used.");
                return null;
            }else{
                UserModel userModel = new UserModel();
                userModel.setUsername(username);
                userModel.setPassword(password);
                userModel.setEmail(email);

                return userRepository.save(userModel);
            }
        }
    }

    public UserModel authenticate(String username, String password){
        return userRepository.findByUsernameAndPassword(username, password).orElse(null);
    }

}
