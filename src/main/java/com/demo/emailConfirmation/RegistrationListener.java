package com.demo.emailConfirmation;

import com.demo.DTO.IUserService;
import com.demo.domain.User;
import com.demo.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {


    Logger LOGGER = LoggerFactory.getLogger(RegistrationListener.class);
    @Autowired
    private IUserService service;

    @Autowired
    MessageService messageService;

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {


        this.confirmRegistration(event);
    }


    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);


        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        LOGGER.info("Subject ");
        String confirmationUrl
                = event.getAppUrl() + "/v1/verification?token=" + token;


        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText("to Confirm yor e mail registration click on the link below\n" + "http://localhost:8080" + confirmationUrl);

        javaMailSender.send(email);
        LOGGER.info("Message Sent");
    }


}
