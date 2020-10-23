package de.dlh.lhind.annualleave.event;

import de.dlh.lhind.annualleave.model.Leave;
import de.dlh.lhind.annualleave.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApprovedLeaveRequestListener implements ApplicationListener<OnApprovedLeaveRequestEvent> {
    private final EmailService emailService;

    @Autowired
    public ApprovedLeaveRequestListener(EmailService emailService) {
        this.emailService = emailService;
    }
    @Override
    public void onApplicationEvent(OnApprovedLeaveRequestEvent onApprovedLeaveRequestEvent) {
        confirmRegistration(onApprovedLeaveRequestEvent);
    }

    private void confirmRegistration(OnApprovedLeaveRequestEvent onApprovedLeaveRequestEvent) {
        Leave leave = onApprovedLeaveRequestEvent.getLeave();
        emailService.sendSimpleMessage(
                leave.getRequestedBy().getEmail(),
                String.format("Approved %s Request", leave.getLeaveTypes().getDescription()),
                String.format("Your %s Request was successfully Approved By Supervisor %s %s",
                        leave.getLeaveTypes().getDescription(),
                        leave.getApproved().get(leave.getApproved().size() -1).getApprovedBy().getFirstName(),
                        leave.getApproved().get(leave.getApproved().size() -1).getApprovedBy().getLastName()));
    }
}
