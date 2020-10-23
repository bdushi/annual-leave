package de.dlh.lhind.annualleave.event;

import de.dlh.lhind.annualleave.model.Leave;
import org.springframework.context.ApplicationEvent;

public class OnApprovedLeaveRequestEvent  extends ApplicationEvent {
    private final Leave leave;
    public OnApprovedLeaveRequestEvent(Leave leave) {
        super(leave);
        this.leave = leave;
    }
    public Leave getLeave() {
        return leave;
    }
}
