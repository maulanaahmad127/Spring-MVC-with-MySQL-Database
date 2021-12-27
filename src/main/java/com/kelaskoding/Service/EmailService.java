package com.kelaskoding.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private MailSender sender;

    public void sendEmail(String to, String subject, String messages){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(messages);
        message.setFrom("maul@gmail.com");
        sender.send(message);
    }
    
}
