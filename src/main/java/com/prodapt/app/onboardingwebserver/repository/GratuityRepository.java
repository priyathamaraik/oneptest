package com.prodapt.app.onboardingwebserver.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prodapt.app.onboardingwebserver.entities.Gratuity;

@Repository
public interface GratuityRepository extends CrudRepository<Gratuity, String> {
	Optional<Gratuity> findByEmpId(String empId);
	Optional<Iterable<Gratuity>> findByEmpName(String empName);
	Optional<Iterable<Gratuity>> findByEmpDept(String empDept);
	Optional<Iterable<Gratuity>> findByDateOfAppointment(long dateOfAppointment);
}
