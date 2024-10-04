package com.rituraj.notification.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final VerificationCodeService verificationCodeService;

    @Autowired
    EmailService(JavaMailSender mailSender, VerificationCodeService verificationCodeService) {
        this.mailSender = mailSender;
        this.verificationCodeService = verificationCodeService;
    }

    public void sendVerificationMail(String email, String baseUrl){
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setSubject("Verification Email");
            String jwtToken = verificationCodeService.generateVerificationCode(email);
            mailMessage.setText("This is code is valid for 5 minutes. Verification code : "+ baseUrl+"/protected/user/verify-code/"+jwtToken);
            mailSender.send(mailMessage);
        }
        catch(Exception e){
            log.error("An error occurred while sending mail: ", e);
        }
    }
}
