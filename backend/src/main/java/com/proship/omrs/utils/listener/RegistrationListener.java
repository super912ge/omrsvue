package com.proship.omrs.utils.listener;

import com.proship.omrs.user.entity.User;
import com.proship.omrs.user.service.UserService;
import com.proship.omrs.utils.event.OnRegistrationCompleteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    UserService userService;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {

        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user,token);
        String recipientEmail = user.getEmail();
        String subject = "OMRS Registration Confirmation";
        String confirmationUrl = event.getAppUrl()+"/user/registrationConfirm?token="+token;
        String message = "Click the link to finish confirmation: ";
                //messageSource.getMessage("message.regSucc",null,event.getLocale());

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientEmail);
        email.setSubject(subject);
        email.setText(message + " rn "+ "http://localhost:8080"+confirmationUrl);
        mailSender.send(email);

    }
}
