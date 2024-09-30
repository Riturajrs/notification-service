package com.rituraj.notification.controller;

import com.rituraj.notification.entity.User;
import com.rituraj.notification.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
public class PublicUserController {

    private final UserService userService;

    @Autowired
    public PublicUserController(UserService userService) {
        this.userService = userService;
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
}
