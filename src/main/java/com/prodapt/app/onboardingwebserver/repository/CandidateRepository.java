package com.prodapt.app.onboardingwebserver.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.prodapt.app.onboardingwebserver.entities.Candidate;

@Repository
public interface CandidateRepository extends PagingAndSortingRepository<Candidate, String> {
	Optional<Candidate> findByEmail(String email);

	Optional<Iterable<Candidate>> findByName(String name);

	Page<Candidate> findAllByName(String name, Pageable pageable);

	Page<Candidate> findAll(Pageable pageable);

	Page<Candidate> findAllByStatus(String status, Pageable pageable);

}
