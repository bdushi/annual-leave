package de.dlh.lhind.annualleave.revision;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AnnualLeaveRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object revisionEntity) {
        AnnualLeaveRevision annualLeaveRevision = (AnnualLeaveRevision)  revisionEntity;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        annualLeaveRevision.setUsername(auth.getName());
    }
}
