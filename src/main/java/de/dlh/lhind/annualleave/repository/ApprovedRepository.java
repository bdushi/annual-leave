package de.dlh.lhind.annualleave.repository;

import de.dlh.lhind.annualleave.model.Approved;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprovedRepository extends JpaRepository<Approved, Long>, JpaSpecificationExecutor<Approved> {
    @Query(
            value = "SELECT A .* FROM APPROVED AS A JOIN LEAVE AS L ON L.ID = A.LEAVE_ID WHERE L.ID = :id",
            nativeQuery = true
    )
    List<Approved> findAllByLeaveId(long id);
}
