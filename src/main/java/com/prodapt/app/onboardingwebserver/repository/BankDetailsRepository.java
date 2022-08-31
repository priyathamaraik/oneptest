package com.prodapt.app.onboardingwebserver.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prodapt.app.onboardingwebserver.entities.BankDetails;

@Repository
public interface BankDetailsRepository extends CrudRepository<BankDetails, String> {
	Optional<BankDetails> findByEmpId(String empId);
	Optional<Iterable<BankDetails>> findByEmpName(String empName);
	Optional<Iterable<BankDetails>> findByDateOfJoining(long dateOfJoining);	
}
