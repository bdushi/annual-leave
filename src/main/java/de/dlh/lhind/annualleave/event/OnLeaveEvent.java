package de.dlh.lhind.annualleave.event;

import de.dlh.lhind.annualleave.model.User;
import org.springframework.context.ApplicationEvent;

public class OnLeaveEvent extends ApplicationEvent {
    private final User user;
    public OnLeaveEvent(User user) {
        super(user);
        this.user = user;
    }
    public User getUser() {
        return user;
    }

}
