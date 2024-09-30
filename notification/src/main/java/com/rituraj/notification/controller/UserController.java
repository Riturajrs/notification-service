package com.rituraj.notification.controller;

import com.rituraj.notification.entity.User;
import com.rituraj.notification.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok().body(Optional.of(userRepository.findAll()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}
