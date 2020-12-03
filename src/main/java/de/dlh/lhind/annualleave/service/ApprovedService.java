package de.dlh.lhind.annualleave.service;

import de.dlh.lhind.annualleave.model.Approved;
import de.dlh.lhind.annualleave.repository.ApprovedRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApprovedService {
    private final ApprovedRepository approvedRepository;
    public ApprovedService(ApprovedRepository approvedRepository) {
        this.approvedRepository = approvedRepository;
    }

    /**
     *
     * SELECT A .* FROM APPROVED AS A
     * JOIN LEAVE_APPROVED AS LA ON A.ID = LA.APPROVED_ID
     * JOIN LEAVE AS L ON L.ID = LA.LEAVE_ID
     * WHERE L.ID = 1
     *
     * @param leaveId
     * @return
     *
     *
     */
    public List<Approved> findAllJoin(long leaveId) {
        return approvedRepository.findAll((Specification<Approved>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            root
                    .join("leave_approved", JoinType.LEFT)
                        .on(
                                criteriaBuilder
                                        .equal(
                                                root.get("approved").get("id"),
                                                root.get("leave_approved").get("approved_id")
                                        )
                        )
                    .join("leave", JoinType.LEFT)
                        .on(
                                criteriaBuilder
                                        .equal(
                                                root.get("leave").get("id"),
                                                root.get("leave_approved").get("leave_id")
                                        )
                        );
            predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("leave").get("id"), leaveId)));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

    /**
     *
     * SELECT A .* FROM APPROVED AS A
     * JOIN LEAVE AS L ON L.ID = A.LEAVE_ID
     * WHERE L.ID = 1
     *
     * @param leaveId
     * @return
     *
     *
     */
    public List<Approved> findAll(long leaveId) {
        return approvedRepository.findAll((Specification<Approved>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("leave_id"), leaveId)));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

    public List<Approved> findApprovedId(long id) {
        return approvedRepository.findApprovedId(id);
    }

    public Page<Approved> approved(long id, String search, Pageable pageable) {
        return approvedRepository.approved(id, "%"+search+"%", pageable);
    }
}
