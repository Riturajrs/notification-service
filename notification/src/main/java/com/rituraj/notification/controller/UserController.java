package com.rituraj.notification.controller;

import com.rituraj.notification.entity.User;
import com.rituraj.notification.repository.UserRepository;
import com.rituraj.notification.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController()
@RequestMapping("/protected/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyUserMail(){
        Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
        String emailAddress = authenticatedUser.getName();
        return ResponseEntity.ok(emailAddress);
    }
}
