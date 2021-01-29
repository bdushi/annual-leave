package de.dlh.lhind.annualleave.repository;

import de.dlh.lhind.annualleave.model.Approved;
import de.dlh.lhind.annualleave.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends RevisionRepository<Authority, Long, Long>, JpaRepository<Authority, Long>, JpaSpecificationExecutor<Authority> {

}
