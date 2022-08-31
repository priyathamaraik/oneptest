package com.prodapt.app.onboardingwebserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.prodapt.app.onboardingwebserver.entities.Candidate;
import com.prodapt.app.onboardingwebserver.entities.HrUser;
import com.prodapt.app.onboardingwebserver.repository.CandidateRepository;
import com.prodapt.app.onboardingwebserver.repository.HrUserRepository;

@Service
public class AuthUserDetailService implements UserDetailsService {

	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private HrUserRepository hrUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {
			if (username.equals("admin")) {
				return User.withUsername("admin").password(passwordEncoder().encode("password")).accountLocked(false).roles("USER", "ADMIN")
						.build();
			} else if (candidateRepository.findByEmail(username).isPresent()) {
				Candidate candidate = candidateRepository.findByEmail(username).get();
				return User.withUsername(candidate.getEmail()).password(candidate.getPassword())
						.accountLocked(!candidate.isEnabled()).roles(candidate.getRole()).build();
			} else if (hrUserRepository.findByEmail(username).isPresent()) {
				HrUser hrUser = hrUserRepository.findByEmail(username).get();
				return User.withUsername(hrUser.getEmail()).password(hrUser.getPassword())
						.accountLocked(!hrUser.isEnabled()).roles(hrUser.getRole()).build();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new UsernameNotFoundException(username);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
