package com.resttest.service;

import com.resttest.model.MailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * Created by kvasa on 06.01.2017.
 */
@Service("mailService")
public class MailService {

    @Autowired
    private MailSender mailSender;

    public void sendMail(MailTemplate mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("kvasar739@gmail.com");
        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getBody());
        mailSender.send(message);
    }

}
