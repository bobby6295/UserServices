package com.demo.emailConfirmation;

import com.demo.domain.User;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private User user;

    private String appUrl;
    private Locale locale;

    public OnRegistrationCompleteEvent(User user, String appurl, Locale local) {
        super(user);
        this.user = user;
        this.locale = local;
        this.appUrl = appurl;


    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Locale getLocal() {
        return locale;
    }

    public void setLocal(Locale local) {
        this.locale = local;
    }
}


