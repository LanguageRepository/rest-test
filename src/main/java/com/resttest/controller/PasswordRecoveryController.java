package com.resttest.controller;

import com.resttest.model.MailTemplate;
import com.resttest.service.MailService;
import com.resttest.service.PasswordChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kvasa on 06.01.2017.
 */
@RestController
@RequestMapping(value = "/mail")
public class PasswordRecoveryController {

    @Autowired
    private MailService mailService;

    @Autowired
    private PasswordChange passwordChange;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public void sendEmail(@RequestBody MailTemplate mailTemplate) {
        mailService.sendMail(mailTemplate);
    }

    @RequestMapping(value = "/changepassword/{username}", method = RequestMethod.POST)
    public String requestToPasswordRecovery(@PathVariable String username) {
        return passwordChange.requestToSendPin(username);
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String getString(@RequestParam String str) {
        return passwordChange.getString(str);
    }
}
