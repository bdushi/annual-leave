package de.dlh.lhind.annualleave.dto;

import de.dlh.lhind.annualleave.model.Approved;
import de.dlh.lhind.annualleave.model.LeaveTypes;
import de.dlh.lhind.annualleave.model.User;

import java.time.ZonedDateTime;
import java.util.List;

public class LeaveDto {
    private final ZonedDateTime createDate;
    private final ZonedDateTime startDate;
    private final ZonedDateTime endDate;
    private final String description;
    private final String comment;
    private final User requestedBy;
    private final LeaveTypes leaveTypes;
    private final List<Approved> approved;

    public LeaveDto(
            ZonedDateTime createDate,
            ZonedDateTime startDate,
            ZonedDateTime endDate,
            String description,
            String comment,
            User requestedBy,
            LeaveTypes leaveTypes,
            List<Approved> approved
    ) {
        this.createDate = createDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.comment = comment;
        this.requestedBy = requestedBy;
        this.leaveTypes = leaveTypes;
        this.approved = approved;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    public String getComment() {
        return comment;
    }

    public User getRequestedBy() {
        return requestedBy;
    }

    public LeaveTypes getLeaveTypes() {
        return leaveTypes;
    }

    public List<Approved> getApproved() {
        return approved;
    }
}
