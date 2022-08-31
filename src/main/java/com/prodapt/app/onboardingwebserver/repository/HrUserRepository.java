package com.prodapt.app.onboardingwebserver.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prodapt.app.onboardingwebserver.entities.HrUser;

@Repository
public interface HrUserRepository extends CrudRepository<HrUser, String> {
	Optional<HrUser> findByEmail(String email);
	Optional<Iterable<HrUser>> findByName(String name);
	Optional<HrUser> findByEmpCode(String empCode);
}
