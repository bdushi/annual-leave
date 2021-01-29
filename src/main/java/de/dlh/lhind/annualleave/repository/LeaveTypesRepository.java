package de.dlh.lhind.annualleave.repository;

import de.dlh.lhind.annualleave.model.Leave;
import de.dlh.lhind.annualleave.model.LeaveTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveTypesRepository extends RevisionRepository<LeaveTypes, Long, Long>, JpaRepository<LeaveTypes, Long> {
}
