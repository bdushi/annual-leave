package de.dlh.lhind.annualleave.repository;

import de.dlh.lhind.annualleave.model.Approved;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApprovedRepository extends RevisionRepository<Approved, Long, Long>, JpaRepository<Approved, Long>, JpaSpecificationExecutor<Approved> {
    @Query(
            value = "" +
                    "SELECT A .* FROM APPROVED AS A " +
                    "LEFT JOIN LEAVE_APPROVED AS LA ON LA.APPROVED_ID = A.ID " +
                    "WHERE LA.LEAVE_ID = :id ORDER BY A.ID DESC LIMIT 1",
            nativeQuery = true
    )
    Optional<Approved> findApprovedId(long id);

    @Query(
            value = "" +
                    "SELECT A .* FROM APPROVED AS A " +
                    "LEFT JOIN LEAVE AS L ON L.ID = A.LEAVE_ID " +
                    "LEFT JOIN USER AS U ON U.ID = A.APPROVED_BY_ID " +
                    "WHERE L.ID = :id " +
                    "OR A.comment LIKE :search " +
                    "OR U.username LIKE :search",
            nativeQuery = true
    )
    Page<Approved> approved(long id, String search, Pageable pageable);
}
