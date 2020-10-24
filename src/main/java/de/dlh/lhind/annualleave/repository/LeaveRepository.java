package de.dlh.lhind.annualleave.repository;

import de.dlh.lhind.annualleave.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
}
