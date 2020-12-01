package de.dlh.lhind.annualleave.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table
public class Leave implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, insertable = false, unique = true, nullable = false)
    private long id;
    private ZonedDateTime createDate;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private String description;
    private String comment;
    @OneToOne
    private User requestedBy;
    @OneToOne
    private LeaveTypes leaveTypes;
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, targetEntity = Approved.class)
    @JoinColumn(name = "leave_id", insertable = false, updatable = false)
    private List<Approved> approved;

    public Leave() {

    }
    public Leave(
            long id,
            ZonedDateTime createDate,
            ZonedDateTime startDate,
            ZonedDateTime endDate,
            String description,
            String comment,
            User requestedBy,
            LeaveTypes leaveTypes,
            List<Approved> approved
    ) {
        this.id = id;
        this.createDate = createDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.comment = comment;
        this.requestedBy = requestedBy;
        this.leaveTypes = leaveTypes;
        this.approved = approved;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(User requestedBy) {
        this.requestedBy = requestedBy;
    }

    public LeaveTypes getLeaveTypes() {
        return leaveTypes;
    }

    public void setLeaveTypes(LeaveTypes leaveTypes) {
        this.leaveTypes = leaveTypes;
    }

    public List<Approved> getApproved() {
        return approved;
    }

    public void setApproved(List<Approved> approved) {
        this.approved = approved;
    }
}
