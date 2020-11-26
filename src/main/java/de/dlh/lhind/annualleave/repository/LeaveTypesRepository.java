package de.dlh.lhind.annualleave.repository;

import de.dlh.lhind.annualleave.model.LeaveTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveTypesRepository extends JpaRepository<LeaveTypes, Long> {
}
