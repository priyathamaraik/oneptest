package com.prodapt.app.onboardingwebserver.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prodapt.app.onboardingwebserver.entities.Declarations;

@Repository
public interface DeclarationsRepository extends CrudRepository<Declarations, String> {
	Optional<Declarations> findByEmpId(String empId);
	Optional<Iterable<Declarations>> findByEmpName(String empName);	
}
