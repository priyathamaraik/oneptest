package com.prodapt.app.onboardingwebserver.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.prodapt.app.onboardingwebserver.entities.Mediclaim;

public interface MediclaimRepository extends CrudRepository<Mediclaim, String> {
	Optional<Mediclaim> findByEmpId(String empId);
	Optional<Iterable<Mediclaim>> findByName(String name);
	Optional<Iterable<Mediclaim>> findByDateOfBirth(long dateOfBirth);
	Optional<Iterable<Mediclaim>> findByDateOfJoining(long dateOfJoining);
}
