package de.dlh.lhind.annualleave.service;

import de.dlh.lhind.annualleave.model.Approved;
import de.dlh.lhind.annualleave.model.Leave;
import de.dlh.lhind.annualleave.model.User;
import de.dlh.lhind.annualleave.repository.ApprovedRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
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
     * https://stackoverflow.com/questions/31841471/spring-data-jpa-specification-for-a-manytomany-unidirectional-relationship
     *
     * SELECT A .* FROM APPROVED AS A
     * JOIN LEAVE AS L ON L.ID = A.LEAVE_ID
     * WHERE L.ID = 1
     *
     * @param leaveId
     * @param search
     * @param pageable
     * @return Page
     * Root<Approved> approvedRoot = root
     *
     */
    public Page<Approved> searchApproved(long leaveId, String search, Pageable pageable) {
        return approvedRepository.findAll((Specification<Approved>) (root, criteriaQuery, criteriaBuilder) -> {
            criteriaQuery.distinct(true);
            Root<Leave> leaveRoot = criteriaQuery.from(Leave.class);
            Expression<Collection<Approved>> leaveApproved = leaveRoot.get("approved");
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(
                    criteriaBuilder.and(
                            criteriaBuilder.equal(leaveRoot.get("id"), leaveId),
                            criteriaBuilder.isMember(root, leaveApproved)
                    )
            );
            if(!search.equals("null")) {
                predicates.add(
                criteriaBuilder.or(
                        criteriaBuilder.like(
                                root.get("comment"),
                                "%"+search+"%"
                        ),
                        criteriaBuilder.like(
                                root.get("approvedBy").get("username"),
                                "%"+search+"%"
                        )
                ));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);
    }

    public List<Approved> findApprovedId(long id) {
        return approvedRepository.findApprovedId(id);
    }

    public Page<Approved> approved(long id, String search, Pageable pageable) {
        return approvedRepository.approved(id, "%"+search+"%", pageable);
    }
}
