package com.prodapt.app.onboardingwebserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prodapt.app.onboardingwebserver.entities.JwtRequest;
import com.prodapt.app.onboardingwebserver.entities.JwtResponse;
import com.prodapt.app.onboardingwebserver.services.AuthUserDetailService;
import com.prodapt.app.onboardingwebserver.utility.JwtUtility;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private JwtUtility jwtUtility;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthUserDetailService userService;

	@PostMapping()
	public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
			
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}

		final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());

		final String token = jwtUtility.generateToken(userDetails);

		return new JwtResponse(token);
	}
	

}
