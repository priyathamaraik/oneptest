package com.prodapt.app.onboardingwebserver.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.prodapt.app.onboardingwebserver.entities.EPFNomination;

public interface EPFNominationRepository extends CrudRepository<EPFNomination, String> {
	Optional<EPFNomination> findByEmpId(String empId);
	Optional<Iterable<EPFNomination>> findByEmpName(String empName);
}
