package de.dlh.lhind.annualleave.repository;

import de.dlh.lhind.annualleave.model.Authority;
import de.dlh.lhind.annualleave.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long>, JpaSpecificationExecutor<Authority> {

}
