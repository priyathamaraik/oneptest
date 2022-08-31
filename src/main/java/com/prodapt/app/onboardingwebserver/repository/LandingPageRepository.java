package com.prodapt.app.onboardingwebserver.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prodapt.app.onboardingwebserver.entities.LandingPage;

@Repository
public interface LandingPageRepository extends CrudRepository<LandingPage, String> {
	
}
