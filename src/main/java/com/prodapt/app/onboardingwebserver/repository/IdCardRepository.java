package com.prodapt.app.onboardingwebserver.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prodapt.app.onboardingwebserver.entities.IdCard;

@Repository
public interface IdCardRepository extends CrudRepository<IdCard, String> {
	Optional<IdCard> findByEmpId(String empId);
	
}
