package com.rituraj.notification.controller;

import com.rituraj.notification.entity.User;
import com.rituraj.notification.repository.UserRepository;
import com.rituraj.notification.service.EmailService;
import com.rituraj.notification.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@RestController()
@RequestMapping("/protected/user")
public class UserController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send-code")
    public ResponseEntity<?> verifyUserMail(HttpServletRequest request) {
        String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath(null)
                .build()
                .toUriString();
        Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
        String emailAddress = authenticatedUser.getName();
        emailService.sendVerificationMail(emailAddress, baseUrl);
        return ResponseEntity.ok("Mail sent!");
    }

    @GetMapping("/verify-code")
    public ResponseEntity<?> verifyUserCode(@RequestParam(name = "hash",
            defaultValue = "defaultHash") String JwtHash) {
        emailService.verifyEmailAddress(JwtHash);
        return ResponseEntity.ok("Code verified!");
    }
}
