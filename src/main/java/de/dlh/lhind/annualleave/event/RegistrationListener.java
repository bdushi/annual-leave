package de.dlh.lhind.annualleave.event;

import de.dlh.lhind.annualleave.event.OnRegistrationCompleteEvent;
import de.dlh.lhind.annualleave.model.User;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent onRegistrationCompleteEvent) {

    }


    // Need to be sent asynchronous
    /*private confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.user
        emailService.sendSimpleMessageUsingTemplate(
                "brunodushi@gmail.com",
                "User Registration",
                "User was successfully Registered ${event.appUrl} with UserName: ${user.username} and Password: ${event.password}")
    }*/
}