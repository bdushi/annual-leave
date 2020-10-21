package de.dlh.lhind.annualleave.event;


import de.dlh.lhind.annualleave.model.User;
import org.springframework.context.ApplicationEvent;

public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private final User user;
    private final String appUrl;
    private final String password;
    public OnRegistrationCompleteEvent(User user, String appUrl, String password) {
        super(user);
        this.user = user;
        this.appUrl = appUrl;
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public String getPassword() {
        return password;
    }
}