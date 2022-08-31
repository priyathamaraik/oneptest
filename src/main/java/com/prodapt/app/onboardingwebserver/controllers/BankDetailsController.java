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

import com.prodapt.app.onboardingwebserver.entities.BankDetails;
import com.prodapt.app.onboardingwebserver.repository.BankDetailsRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/bank-details")
public class BankDetailsController {
	
	Logger logger = LoggerFactory.getLogger(BankDetailsController.class); 
	
	@Autowired
	private BankDetailsRepository bankDetailsRepository;
	
	@PostMapping("/save")
	public ResponseEntity<BankDetails> save(@Valid @RequestBody BankDetails bankDetails) {
		if (bankDetailsRepository.findByEmpId(bankDetails.getEmpId()).isPresent()) {
			logger.error("Bank Details already exists, POST operation cannot be performed");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(bankDetailsRepository.findByEmpId(bankDetails.getEmpId()).get());
		} else {
			logger.info("Bank Details Saved");
			return ResponseEntity.ok(bankDetailsRepository.save(bankDetails));
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<BankDetails> update(@Valid @RequestBody BankDetails bankDetails, @PathVariable("id") String id) {
		if (bankDetailsRepository.existsById(id)) {
			logger.info("Bank Details Updated");
			bankDetails.setModifiedOn(Instant.now().toEpochMilli());
			return ResponseEntity.ok(bankDetailsRepository.save(bankDetails));
		} else {
			logger.error("Bank Details doesn't exist, PUT operation cannot be performed");
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<BankDetails> delete(@PathVariable("id") String id) {
		if (bankDetailsRepository.existsById(id)) {
			bankDetailsRepository.deleteById(id);
			logger.info("Bank Details Deleted");
			return ResponseEntity.noContent().build();
		} else {
			logger.error("Bank Details doesn't exist, DELETE operation cannot be performed");
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/get/all")
	public ResponseEntity<Iterable<BankDetails>> findAll() {
		return ResponseEntity.ok(bankDetailsRepository.findAll());
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Optional<BankDetails>> findById(@PathVariable("id") String id) {
		return ResponseEntity.ok(bankDetailsRepository.findById(id));
	}
	
	@GetMapping("/get/empid/{empid}")
	public ResponseEntity<Optional<BankDetails>> findByEmpId(@PathVariable("empid") String empId){
		return ResponseEntity.ok(bankDetailsRepository.findByEmpId(empId));
	}
	
	@GetMapping("get/emp-name/{emp-name}")
	public ResponseEntity<Optional<Iterable<BankDetails>>> findByEmpName(@PathVariable("emp-name") String empName) {
		return ResponseEntity.ok(bankDetailsRepository.findByEmpName(empName));
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
