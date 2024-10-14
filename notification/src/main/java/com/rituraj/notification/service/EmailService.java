package com.rituraj.notification.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final VerificationCodeService verificationCodeService;
    private final UserService userService;

    @Autowired
    EmailService(JavaMailSender mailSender, VerificationCodeService verificationCodeService, UserService userService) {
        this.mailSender = mailSender;
        this.verificationCodeService = verificationCodeService;
        this.userService = userService;
    }

    public void sendVerificationMail(String email, String baseUrl) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setSubject("Verification Email");
            String jwtToken = verificationCodeService.generateVerificationCode(email);
            mailMessage.setText("This is code is valid for 5 minutes. \n "
                    + "Verification code : " + baseUrl
                    + "/protected/user/verify-code?hash=" + jwtToken + "/");
            mailSender.send(mailMessage);
        } catch (Exception e) {
            log.error("An error occurred while sending mail: ", e);
        }
    }

    public void verifyEmailAddress(String JwtHash) {
        try {
            String decodedJwtHash = verificationCodeService.decodeVerificationCode(JwtHash);
            String username;
            if (decodedJwtHash.endsWith("verified")) {
                username = decodedJwtHash.split("verified")[0];
            } else {
                throw new Exception("Invalid verification code");
            }
            if (!userService.verifyUser(username)) {
                throw new Exception("User validation failed");
            }
        } catch (Exception e) {
            log.error("An error occurred while verifying code: ", e);
        }
    }
}
