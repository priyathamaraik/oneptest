package com.prodapt.app.onboardingwebserver.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.prodapt.app.onboardingwebserver.entities.EPFKYCDetails;

public interface EPFKYCRepository extends CrudRepository<EPFKYCDetails, String> {
	
	Optional<EPFKYCDetails> findByEmpId(String empId);
	Optional<Iterable<EPFKYCDetails>> findByDateOfJoining(long dateOfJoining);
}
