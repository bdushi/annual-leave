package de.dlh.lhind.annualleave.service;

import de.dlh.lhind.annualleave.model.Authority;
import de.dlh.lhind.annualleave.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorityService {
    private AuthorityRepository authorityRepository;
    @Autowired
    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }
    public List<Authority> findByAuthority(String authority) {
        return authorityRepository.findAll((Specification<Authority>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("authority"), authority)));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
