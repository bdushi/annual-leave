package de.dlh.lhind.annualleave.event;

import de.dlh.lhind.annualleave.model.Leave;
import de.dlh.lhind.annualleave.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class LeaveListener implements ApplicationListener<OnLeaveEvent> {
    private final EmailService emailService;
    @Autowired
    public LeaveListener(EmailService emailService) {
        this.emailService = emailService;
    }
    @Override
    public void onApplicationEvent(OnLeaveEvent onLeaveEvent) {
        confirmRegistration(onLeaveEvent);
    }

    private void confirmRegistration(OnLeaveEvent onLeaveEvent) {
        Leave leave = onLeaveEvent.getLeave();
        emailService.sendSimpleMessage(
                leave.getRequestedBy().getEmail(),
                String.format("%s", leave.getLeaveTypes().getDescription()),
                String.format("Your %s Request was successfully registered", leave.getLeaveTypes().getDescription()));
    }
}
