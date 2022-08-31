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

import com.prodapt.app.onboardingwebserver.entities.EPFNomination;
import com.prodapt.app.onboardingwebserver.entities.EPFNominee;
import com.prodapt.app.onboardingwebserver.entities.EPSFamily;
import com.prodapt.app.onboardingwebserver.repository.EPFNominationRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/epf-nomination")
public class EPFNominationController {

	@Autowired
	private EPFNominationRepository epfNominationRepository;
	
	@PostMapping("/save")
	public ResponseEntity<EPFNomination> save(@Valid @RequestBody EPFNomination epfNomination) {
		if (epfNominationRepository.findByEmpId(epfNomination.getEmpId()).isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(epfNominationRepository.findByEmpId(epfNomination.getEmpId()).get());
		} else {
			return ResponseEntity.ok(epfNominationRepository.save(epfNomination));
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<EPFNomination> update(@PathVariable("id") String id, @Valid @RequestBody EPFNomination epfNomination) {
		if (epfNominationRepository.existsById(id)) {
			epfNomination.setModifiedOn(Instant.now().toEpochMilli());
			return ResponseEntity.ok(epfNominationRepository.save(epfNomination));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<EPFNomination> delete(@PathVariable("id") String id) {
		if (epfNominationRepository.existsById(id)) {
			epfNominationRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/get/all")
	public ResponseEntity<Iterable<EPFNomination>> findAll() {
		return ResponseEntity.ok(epfNominationRepository.findAll());
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Optional<EPFNomination>> findById(@PathVariable("id") String id) {
		return ResponseEntity.ok(epfNominationRepository.findById(id));
	}
	
	@GetMapping("/get/empid/{empid}")
	public ResponseEntity<Optional<EPFNomination>> findByEmpId(@PathVariable("empid") String empId){
		return ResponseEntity.ok(epfNominationRepository.findByEmpId(empId));
	}
	
	@GetMapping("get/emp-name/{emp-name}")
	public ResponseEntity<Optional<Iterable<EPFNomination>>> findByEmpName(@PathVariable("emp-name") String empName) {
		return ResponseEntity.ok(epfNominationRepository.findByEmpName(empName));
	}
	
	@PostMapping("/{id}/save-epf-nominee")
	public ResponseEntity<EPFNomination> saveEPFNominee(@PathVariable("id") String id, @Valid @RequestBody EPFNominee nominee) {
		if (epfNominationRepository.existsById(id)) {
			EPFNomination epfNomination = epfNominationRepository.findById(id).get();
			List<EPFNominee> nomineeList = new ArrayList<EPFNominee>();
			if (epfNomination.getEpfNomineeList() == null ) {
				nomineeList.add(nominee);
			} else {
				nomineeList.addAll(epfNomination.getEpfNomineeList());
				nomineeList.add(nominee);
			}
			epfNomination.setEpfNomineeList(nomineeList);
			return ResponseEntity.ok(epfNominationRepository.save(epfNomination));	
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}/update-epf-nominee/{nomId}")
	public ResponseEntity<EPFNomination> updateEPFNominee(@PathVariable("id") String id, @PathVariable("nomId") String nomId, @Valid @RequestBody EPFNominee nominee) {
		if (epfNominationRepository.existsById(id)) {
			EPFNomination epfNomination = epfNominationRepository.findById(id).get();
			List<EPFNominee> nomineeList = epfNomination.getEpfNomineeList();
			if (nomineeList != null) {
				for (int i=0; i<nomineeList.size(); i++) {
					if (nomineeList.get(i).getId().equals(nomId)) {
						nomineeList.set(i, nominee);
						break;
					}
				}
				epfNomination.setEpfNomineeList(nomineeList);
			}
			return ResponseEntity.ok(epfNominationRepository.save(epfNomination));
			
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}/delete-epf-nominee/{nomId}")
	public ResponseEntity<EPFNomination> deleteEPFNominee(@PathVariable("id") String id, @PathVariable("nomId") String nomId) {
		if (epfNominationRepository.existsById(id)) {
			EPFNomination epfNominations = epfNominationRepository.findById(id).get();
			List<EPFNominee> addressList = epfNominations.getEpfNomineeList();
			if (addressList != null) {
				for (int i=0; i<addressList.size(); i++) {
					if (addressList.get(i).getId().equals(nomId)) {
						addressList.remove(i);
						break;
					}
				}
				epfNominations.setEpfNomineeList(addressList);
			}
			return ResponseEntity.ok(epfNominationRepository.save(epfNominations));
			
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/{id}/save-eps-family")
	public ResponseEntity<EPFNomination> saveFamily(@PathVariable("id") String id, @Valid @RequestBody EPSFamily family) {
		if (epfNominationRepository.existsById(id)) {
			EPFNomination epfNomination = epfNominationRepository.findById(id).get();
			List<EPSFamily> familyList = new ArrayList<EPSFamily>();
			if (epfNomination.getEpsFamilyList() == null ) {
				familyList.add(family);
			} else {
				familyList.addAll(epfNomination.getEpsFamilyList());
				familyList.add(family);
			}
			epfNomination.setEpsFamilyList(familyList);
			return ResponseEntity.ok(epfNominationRepository.save(epfNomination));	
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}/update-eps-family/{famId}")
	public ResponseEntity<EPFNomination> updatefamily(@PathVariable("id") String id, @PathVariable("famId") String famId , @Valid @RequestBody EPSFamily family) {
		if (epfNominationRepository.existsById(id)) {
			EPFNomination epfNomination = epfNominationRepository.findById(id).get();
			List<EPSFamily> familyList = epfNomination.getEpsFamilyList();
			if (familyList != null) {
				for (int i=0; i<familyList.size(); i++) {
					if (familyList.get(i).getId().equals(famId)) {
						familyList.set(i, family);
						break;
					}
				}
				epfNomination.setEpsFamilyList(familyList);
			}
			return ResponseEntity.ok(epfNominationRepository.save(epfNomination));
			
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}/delete-eps-family/{famId}")
	public ResponseEntity<EPFNomination> deleteFamily(@PathVariable("id") String id, @PathVariable("famId") String famId) {
		if (epfNominationRepository.existsById(id)) {
			EPFNomination epfNominations = epfNominationRepository.findById(id).get();
			List<EPSFamily> familyList = epfNominations.getEpsFamilyList();
			if (familyList != null) {
				for (int i=0; i<familyList.size(); i++) {
					if (familyList.get(i).getId().equals(famId)) {
						familyList.remove(i);
						break;
					}
				}
				epfNominations.setEpsFamilyList(familyList);
			}
			return ResponseEntity.ok(epfNominationRepository.save(epfNominations));
			
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/{id}/save-nominee")
	public ResponseEntity<EPFNomination> saveNominee(@PathVariable("id") String id, @Valid @RequestBody EPFNominee nominee) {
		if (epfNominationRepository.existsById(id)) {
			EPFNomination epfNomination = epfNominationRepository.findById(id).get();
			List<EPFNominee> nomineeList = new ArrayList<EPFNominee>();
			if (epfNomination.getNomineeList() == null ) {
				nomineeList.add(nominee);
			} else {
				nomineeList.addAll(epfNomination.getNomineeList());
				nomineeList.add(nominee);
			}
			epfNomination.setNomineeList(nomineeList);
			return ResponseEntity.ok(epfNominationRepository.save(epfNomination));	
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}/update-nominee/{nomId}")
	public ResponseEntity<EPFNomination> updateNominee(@PathVariable("id") String id, @PathVariable("nomId") String nomId , @Valid @RequestBody EPFNominee nominee) {
		if (epfNominationRepository.existsById(id)) {
			EPFNomination epfNomination = epfNominationRepository.findById(id).get();
			List<EPFNominee> nomineeList = epfNomination.getNomineeList();
			if (nomineeList != null) {
				for (int i=0; i<nomineeList.size(); i++) {
					if (nomineeList.get(i).getId().equals(nomId)) {
						nomineeList.set(i, nominee);
						break;
					}
				}
				epfNomination.setNomineeList(nomineeList);
			}
			return ResponseEntity.ok(epfNominationRepository.save(epfNomination));
			
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}/delete-nominee/{nomId}")
	public ResponseEntity<EPFNomination> deleteNominee(@PathVariable("id") String id, @PathVariable("nomId") String nomId) {
		if (epfNominationRepository.existsById(id)) {
			EPFNomination epfNominations = epfNominationRepository.findById(id).get();
			List<EPFNominee> addressList = epfNominations.getNomineeList();
			if (addressList != null) {
				for (int i=0; i<addressList.size(); i++) {
					if (addressList.get(i).getId().equals(nomId)) {
						addressList.remove(i);
						break;
					}
				}
				epfNominations.setNomineeList(addressList);
			}
			return ResponseEntity.ok(epfNominationRepository.save(epfNominations));
			
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
