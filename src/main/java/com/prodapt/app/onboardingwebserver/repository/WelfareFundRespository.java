package com.prodapt.app.onboardingwebserver.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prodapt.app.onboardingwebserver.entities.WelfareFund;

@Repository
public interface WelfareFundRespository extends CrudRepository<WelfareFund, String> {
	Optional<WelfareFund> findByEmpId(String empId);

	Optional<Iterable<WelfareFund>> findByEmpName(String empName);

	Optional<Iterable<WelfareFund>> findByDateOfJoining(long dateOfJoining);
}
