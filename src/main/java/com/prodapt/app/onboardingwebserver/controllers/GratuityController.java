package com.prodapt.app.onboardingwebserver.controllers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

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

import com.prodapt.app.onboardingwebserver.entities.Gratuity;
import com.prodapt.app.onboardingwebserver.entities.GratuityNominee;
import com.prodapt.app.onboardingwebserver.repository.GratuityRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/gratuity")
public class GratuityController {
	
	@Autowired
	private GratuityRepository gratuityRepository;
	
	@PostMapping("/save") 
	public ResponseEntity<Gratuity> save(@Valid @RequestBody Gratuity gratuity) {
		if (gratuityRepository.findByEmpId(gratuity.getEmpId()).isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(gratuityRepository.findByEmpId(gratuity.getEmpId()).get());
		} else {
			return ResponseEntity.ok(gratuityRepository.save(gratuity));
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Gratuity> update(@PathVariable("id") String id, @Valid @RequestBody Gratuity gratuity) {
		if (gratuityRepository.existsById(id)) {
			gratuity.setModifiedOn(Instant.now().toEpochMilli());
			return ResponseEntity.ok(gratuityRepository.save(gratuity));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Gratuity> delete(@PathVariable("id") String id) {
		if (gratuityRepository.existsById(id)) {
			gratuityRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/get/all")
	public ResponseEntity<Iterable<Gratuity>> findAll() {
		return ResponseEntity.ok(gratuityRepository.findAll());
	}
	
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Optional<Gratuity>> findById(@PathVariable("id") String id) {
		return ResponseEntity.ok(gratuityRepository.findById(id));
	}
	
	@GetMapping("/get/empid/{empid}")
	public ResponseEntity<Optional<Gratuity>> findByEmpId(@PathVariable("empid") String empId){
		return ResponseEntity.ok(gratuityRepository.findByEmpId(empId));
	}
	
	@GetMapping("get/emp-name/{emp-name}")
	public ResponseEntity<Optional<Iterable<Gratuity>>> findByEmpName(@PathVariable("emp-name") String empName) {
		return ResponseEntity.ok(gratuityRepository.findByEmpName(empName));
	}
	
	@PostMapping("/{id}/save-nominee")
	public ResponseEntity<Gratuity> saveNominee(@PathVariable("id") String id, @Valid @RequestBody GratuityNominee nominee) {
		if (gratuityRepository.existsById(id)) {
			Gratuity gratuity = gratuityRepository.findById(id).get();
			List<GratuityNominee> nomineeList = new ArrayList<GratuityNominee>();
			if (gratuity.getNomineeList() == null ) {
				nomineeList.add(nominee);
			} else {
				nomineeList.addAll(gratuity.getNomineeList());
				nomineeList.add(nominee);
			}
			gratuity.setNomineeList(nomineeList);
			return ResponseEntity.ok(gratuityRepository.save(gratuity));
			
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}/update-nominee/{nomId}")
	public ResponseEntity<Gratuity> updateNominee(@PathVariable("id") String id, @PathVariable("nomId") String nomId , @Valid @RequestBody GratuityNominee nominee) {
		if (gratuityRepository.existsById(id)) {
			Gratuity gratuity = gratuityRepository.findById(id).get();
			List<GratuityNominee> nomineeList = gratuity.getNomineeList();
			if (nomineeList != null) {
				for (int i=0; i<nomineeList.size(); i++) {
					if (nomineeList.get(i).getId().equals(nomId)) {
						nomineeList.set(i, nominee);
						break;
					}
				}
				gratuity.setNomineeList(nomineeList);
			}
			return ResponseEntity.ok(gratuityRepository.save(gratuity));
			
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}/delete-nominee/{nomId}")
	public ResponseEntity<Gratuity> deleteAddress(@PathVariable("id") String id, @PathVariable("nomId") String nomId) {
		if (gratuityRepository.existsById(id)) {
			Gratuity candidateDetails = gratuityRepository.findById(id).get();
			List<GratuityNominee> addressList = candidateDetails.getNomineeList();
			if (addressList != null) {
				for (int i=0; i<addressList.size(); i++) {
					if (addressList.get(i).getId().equals(nomId)) {
						addressList.remove(i);
						break;
					}
				}
				candidateDetails.setNomineeList(addressList);
			}
			return ResponseEntity.ok(gratuityRepository.save(candidateDetails));
			
		} else {
			return ResponseEntity.notFound().build();
		}
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
