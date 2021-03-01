package com.example.SpringFirstProject.service;

import com.example.SpringFirstProject.model.User;
import com.example.SpringFirstProject.repository.UserRepository;
import com.example.SpringFirstProject.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public RegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void register(UserRequest userRequest){
        User user = new User(userRequest.getLogin(), passwordEncoder.encode(userRequest.getPasswd()));
         User existingUser = userRepository.findByLogin(userRequest.getLogin());
         if(existingUser == null){
             userRepository.save(user);
         }
        else throw new RegisterServiceException("istnieje już taki użytkownik pozdrawiam cieplutko ");
        System.out.println(userRepository.findAll());


    }


}
