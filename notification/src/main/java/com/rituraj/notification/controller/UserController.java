package com.rituraj.notification.controller;

import com.rituraj.notification.entity.User;
import com.rituraj.notification.repository.UserRepository;
import com.rituraj.notification.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController()
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user){
        if( userService.saveUser(user) ){
            return ResponseEntity.ok().body("User created successfully!");
        }
        else{
            return ResponseEntity.internalServerError().body("Something went wrong!");
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUserMail(@Valid @RequestBody User user){}
}
