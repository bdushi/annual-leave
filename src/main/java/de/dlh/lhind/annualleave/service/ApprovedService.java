package de.dlh.lhind.annualleave.service;

import de.dlh.lhind.annualleave.model.Approved;
import de.dlh.lhind.annualleave.model.Leave;
import de.dlh.lhind.annualleave.repository.ApprovedRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

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
     * https://www.google.com/search?client=firefox-b-d&q=JpaSpecificationExecutor+comnpare+many+to+many+relationship
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

    public Approved findApprovedId(long id) throws Throwable {
        return approvedRepository
                .findApprovedId(id)
                .orElseThrow((Supplier<Throwable>) () -> new RuntimeException("Approved with Id:" + id + " was not Founded"));
    }

    /**
     * https://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl/
     * https://www.objectdb.com/java/jpa/query/criteria
     * https://www.codota.com/code/java/methods/javax.persistence.criteria.CriteriaBuilder/least
     *
     */
    public Optional<Approved> findLeastApprovedById(long id) {
        return approvedRepository.findOne((Specification<Approved>) (root, criteriaQuery, criteriaBuilder) -> {
            criteriaQuery.distinct(true);
            Root<Leave> leaveRoot = criteriaQuery.from(Leave.class);
            Expression<Collection<Approved>> leaveApproved = leaveRoot.get("approved");
            criteriaQuery
                    .orderBy(criteriaBuilder.desc(root.get("id")));
//            CriteriaQuery<Long> criteriaBuilderQuery = criteriaBuilder.createQuery(Long.class);
//            criteriaBuilderQuery.select(criteriaBuilder.count(criteriaBuilderQuery.from(Approved.class)));
//            Expression<Approved> approved = criteriaBuilder.least(root.as());
            return criteriaBuilder.and(
                    criteriaBuilder.equal(leaveRoot.get("id"), id),
                    criteriaBuilder.isMember(root, leaveApproved)
            );
        });
    }
}
