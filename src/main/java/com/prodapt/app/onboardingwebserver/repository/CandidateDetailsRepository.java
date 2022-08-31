package com.prodapt.app.onboardingwebserver.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prodapt.app.onboardingwebserver.entities.CandidateDetails;

@Repository
public interface CandidateDetailsRepository extends CrudRepository<CandidateDetails, String> {
	Optional<CandidateDetails> findByEmpId(String empId);
	Optional<CandidateDetails> findByemail(String email);
	Optional<Iterable<CandidateDetails>> findByBloodGroup(String bloodGroup);
}
