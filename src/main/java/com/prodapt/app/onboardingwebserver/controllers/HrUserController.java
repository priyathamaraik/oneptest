package com.prodapt.app.onboardingwebserver.controllers;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prodapt.app.onboardingwebserver.entities.HrUser;
import com.prodapt.app.onboardingwebserver.repository.HrUserRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/hr-user")
public class HrUserController {
	
	Logger logger = LoggerFactory.getLogger(HrUserController.class);

	@Autowired
	private HrUserRepository hrUserRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostMapping("/register")
	public ResponseEntity<HrUser> registerCandidate(@Valid @RequestBody HrUser hrUser) {
		Optional<HrUser> cand = hrUserRepository.findByEmail(hrUser.getEmail());
		if (cand.isPresent()) {
			logger.error("HR USER already exists");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(hrUser);
		} else {
			hrUser.setPassword(bCryptPasswordEncoder.encode(hrUser.getPassword()));
			return ResponseEntity.ok(hrUserRepository.save(hrUser));
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<HrUser> updateCandidate(@PathVariable("id") String id,
			@Valid @RequestBody HrUser hrUser) {
		if (hrUserRepository.existsById(id)) {
			hrUser.setModified(Instant.now().toEpochMilli());
			return ResponseEntity.ok(hrUserRepository.save(hrUser));
		} else {
			logger.error("HR USER doesn't exists with Id: " + id);
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/update-password/{id}")
	public ResponseEntity<HrUser> updateCandidatePassword(@PathVariable("id") String id,
			@Valid @RequestBody HrUser hrUser) {
		if (hrUserRepository.existsById(id)) {
			hrUser.setModified(Instant.now().toEpochMilli());
			hrUser.setPassword(bCryptPasswordEncoder.encode(hrUser.getPassword()));
			return ResponseEntity.ok(hrUserRepository.save(hrUser));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HrUser> deleteCandidate(@PathVariable("id") String id) {
		if (hrUserRepository.existsById(id)) {
			hrUserRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("get/all")
	public ResponseEntity<Iterable<HrUser>> getAllCandidates() {
		return ResponseEntity.ok(hrUserRepository.findAll());
	}

	@GetMapping("get/id/{id}")
	public ResponseEntity<Optional<HrUser>> getCandidateById(@PathVariable("id") String id) {
		return ResponseEntity.ok(hrUserRepository.findById(id));
	}

	@GetMapping("get/email/{email}")
	public ResponseEntity<Optional<HrUser>> getCandidateByEmail(@PathVariable("email") String email) {
		Optional<HrUser> hrUser = hrUserRepository.findByEmail(email);
		return ResponseEntity.ok(hrUser);
	}
	
	@GetMapping("get/empcode/{empCode}")
	public ResponseEntity<Optional<HrUser>> getCandidateByEmpCode(@PathVariable("empCode") String empCode) {
		Optional<HrUser> hrUser = hrUserRepository.findByEmpCode(empCode);
		return ResponseEntity.ok(hrUser);
	}

	@GetMapping("get/name/{name}")
	public ResponseEntity<Optional<Iterable<HrUser>>> getCandidateByName(@PathVariable("name") String name) {
		return ResponseEntity.ok(hrUserRepository.findByName(name));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException e) {
		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}
