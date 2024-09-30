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

    EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendVerificationMail(String email){
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setSubject("Verification Email");
            mailMessage.setText("Your verification code has been sent to "+email);
            mailSender.send(mailMessage);
        }
        catch(Exception e){
            log.error("An error occurred while sending mail: ", e);
        }
    }
}
