package de.dlh.lhind.annualleave.event;

import de.dlh.lhind.annualleave.model.User;
import de.dlh.lhind.annualleave.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
class RegistrationListener implements ApplicationListener<OnRegistrationEvent> {
    private final EmailService emailService;

    @Autowired
    public RegistrationListener(EmailService emailService) {
        this.emailService = emailService;
    }
    @Override
    public void onApplicationEvent(OnRegistrationEvent onRegistrationEvent) {
        confirmRegistration(onRegistrationEvent);
    }

    private void confirmRegistration(OnRegistrationEvent event) {
        User user = event.getUser();
        emailService.sendSimpleMessage(
                user.getEmail(),
                "User Registration",
                String.format("User was successfully Registered %s with UserName: %s and Password: %s",event.getAppUrl(),user.getUsername(), event.getPassword()));
    }
}