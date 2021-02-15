package de.dlh.lhind.annualleave.model;

import de.dlh.lhind.annualleave.common.LeaveType;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class LeaveTypes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, insertable = false, unique = true, nullable = false)
    private long id;

    @Enumerated(EnumType.ORDINAL)
//    @Column(columnDefinition = "leave_type")
    private LeaveType types;
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LeaveType getTypes() {
        return types;
    }

    public void setTypes(LeaveType types) {
        this.types = types;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
