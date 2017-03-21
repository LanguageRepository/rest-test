package com.resttest.service;

import com.resttest.dto.user.UserDto;
import com.resttest.model.MailTemplate;
import com.resttest.model.User;
import com.resttest.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * Created by kvasa on 06.01.2017.
 */
@Service
public class PasswordChange {

    @Autowired
    private UserJpaRepository jpaRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;

    @Transactional
    public String generatePin() {
        Random random = new Random();
        String result = "";
        for(int i = 0; i < 4; i++) {
            result += random.nextInt(10);
        }
        return result;
    }

    @Transactional
    public String requestToSendPin(String username) {
        User user = jpaRepository.getUserByUsername(username);
        String pin = generatePin();
        MailTemplate mailTemplate = new MailTemplate(user.getEmail(), "Восстановление пароля", "PIN-код для восстановления пароля: " + pin);
        mailService.sendMail(mailTemplate);
        UserDto dto = new UserDto();
        return pin;
    }

    @Transactional
    public String getString(String str) {
        return str;
    }

}
