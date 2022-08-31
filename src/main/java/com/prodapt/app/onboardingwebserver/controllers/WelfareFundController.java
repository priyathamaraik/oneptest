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

import com.prodapt.app.onboardingwebserver.entities.WelfareFund;
import com.prodapt.app.onboardingwebserver.repository.WelfareFundRespository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/welfare-fund")
public class WelfareFundController {

	Logger logger = LoggerFactory.getLogger(WelfareFundController.class);

	@Autowired
	private WelfareFundRespository welfareFundRepository;

	@PostMapping("/save")
	public ResponseEntity<WelfareFund> save(@Valid @RequestBody WelfareFund welfareFund) {
		if (welfareFundRepository.findByEmpId(welfareFund.getEmpId()).isPresent()) {
			logger.error("Welfare Fund Details already exists, POST operation cannot be performed");
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(welfareFundRepository.findByEmpId(welfareFund.getEmpId()).get());
		} else {
			logger.info("Welfare Fund Details Saved");
			return ResponseEntity.ok(welfareFundRepository.save(welfareFund));
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<WelfareFund> update(@Valid @RequestBody WelfareFund welfareFund,
			@PathVariable("id") String id) {
		if (welfareFundRepository.existsById(id)) {
			logger.info("Welfare Fund Details Updated");
			welfareFund.setModifiedOn(Instant.now().toEpochMilli());
			return ResponseEntity.ok(welfareFundRepository.save(welfareFund));
		} else {
			logger.error("Welfare Fund Details doesn't exist, PUT operation cannot be performed");
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<WelfareFund> delete(@PathVariable("id") String id) {
		if (welfareFundRepository.existsById(id)) {
			welfareFundRepository.deleteById(id);
			logger.info("Welfare Fund Details Deleted");
			return ResponseEntity.noContent().build();
		} else {
			logger.error("Welfare Fund Details doesn't exist, DELETE operation cannot be performed");
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/get/all")
	public ResponseEntity<Iterable<WelfareFund>> findAll() {
		return ResponseEntity.ok(welfareFundRepository.findAll());
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Optional<WelfareFund>> findById(@PathVariable("id") String id) {
		return ResponseEntity.ok(welfareFundRepository.findById(id));
	}

	@GetMapping("/get/empid/{empid}")
	public ResponseEntity<Optional<WelfareFund>> findByEmpId(@PathVariable("empid") String empId) {
		return ResponseEntity.ok(welfareFundRepository.findByEmpId(empId));
	}

	@GetMapping("get/emp-name/{emp-name}")
	public ResponseEntity<Optional<Iterable<WelfareFund>>> findByEmpName(@PathVariable("emp-name") String empName) {
		return ResponseEntity.ok(welfareFundRepository.findByEmpName(empName));
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
