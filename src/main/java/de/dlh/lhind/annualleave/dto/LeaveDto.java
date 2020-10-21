package de.dlh.lhind.annualleave.dto;

import de.dlh.lhind.annualleave.model.Approved;
import de.dlh.lhind.annualleave.model.LeaveTypes;
import de.dlh.lhind.annualleave.model.User;

import java.time.ZonedDateTime;
import java.util.List;

public class LeaveDto {
    private ZonedDateTime createDate;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private String description;
    private String comment;
    private User requestedBy;
    private LeaveTypes leaveTypes;
    private List<Approved> approved;
}
