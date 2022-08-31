package com.prodapt.app.onboardingwebserver.controllers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
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

import com.prodapt.app.onboardingwebserver.entities.Mediclaim;
import com.prodapt.app.onboardingwebserver.entities.Relation;
import com.prodapt.app.onboardingwebserver.repository.MediclaimRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/mediclaim")
public class MediclaimController {
	
	@Autowired
	private MediclaimRepository mediclaimRepository;
	
	@PostMapping("/save")
	public ResponseEntity<Mediclaim> save(@Valid @RequestBody Mediclaim mediclaim) {
		if (mediclaimRepository.findByEmpId(mediclaim.getEmpId()).isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(mediclaim);
		} else {
			return ResponseEntity.ok(mediclaimRepository.save(mediclaim));
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Mediclaim> update(@PathVariable("id") String id, @Valid @RequestBody Mediclaim mediclaim) {
		if (mediclaimRepository.existsById(id)) {
			mediclaim.setModified(new Date().getTime());
			return ResponseEntity.ok(mediclaimRepository.save(mediclaim));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@DeleteMapping("delete/{id}") 
	public ResponseEntity<Mediclaim> delete(@PathVariable("id") String id) {
		if (mediclaimRepository.existsById(id)) {
			mediclaimRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/get/all")
	public ResponseEntity<Iterable<Mediclaim>> getAll() {
		return ResponseEntity.ok(mediclaimRepository.findAll());
	}
	
	
	@GetMapping("/get/id/{id}")
	public ResponseEntity<Optional<Mediclaim>> getById(@PathVariable("id") String id) {
		return ResponseEntity.ok(mediclaimRepository.findById(id));
	}
	
	
	@GetMapping("/get/empid/{empid}")
	public ResponseEntity<Optional<Mediclaim>> getByEmpId(@PathVariable("empid") String empId) {
		return ResponseEntity.ok(mediclaimRepository.findByEmpId(empId));
	}
	
	
	@GetMapping("/get/name/{name}")
	public ResponseEntity<Optional<Iterable<Mediclaim>>> getByName(@PathVariable("name") String name) {
		return ResponseEntity.ok(mediclaimRepository.findByName(name));
	}
	
	@GetMapping("/get/dateofbirth/{dob}")
	public ResponseEntity<Optional<Iterable<Mediclaim>>> getByDateOfBirth(@PathVariable("dob") Long dateOfBirth) {
		return ResponseEntity.ok(mediclaimRepository.findByDateOfBirth(dateOfBirth));
	}
		
	@GetMapping("/get/dateofjoining/{doj}")
	public ResponseEntity<Optional<Iterable<Mediclaim>>> getByDateOfJoining(@PathVariable("dob") Long dateOfJoining) {
		return ResponseEntity.ok(mediclaimRepository.findByDateOfJoining(dateOfJoining));
	}
	
	@PostMapping("/{id}/save-relation")
	public ResponseEntity<Mediclaim> saveRelation(@PathVariable("id") String id, @Valid @RequestBody Relation relation) {
		if (!mediclaimRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		} else {
			Mediclaim mediclaim = mediclaimRepository.findById(id).get();
			mediclaim.setModified(Instant.now().toEpochMilli());
			
			List<Relation> relationList = new ArrayList<>();
			
			if (mediclaim.getRelationList() == null) {
				relationList.add(relation);
			} else {
				relationList.addAll(mediclaim.getRelationList());
				relationList.add(relation);
			}
			mediclaim.setRelationList(relationList);
			return ResponseEntity.ok(mediclaimRepository.save(mediclaim));
		}
	}
	
	@PutMapping("/{id}/update-relation/{rid}")
	public ResponseEntity<Mediclaim> updateRelation(@PathVariable("id") String id, @PathVariable("rid") String rid, @Valid @RequestBody Relation relation) {
		if (!mediclaimRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		} else {
			Mediclaim mediclaim = mediclaimRepository.findById(id).get();
			mediclaim.setModified(Instant.now().toEpochMilli());
			List<Relation> relationList = mediclaim.getRelationList();
			if (relationList != null) {
				for (int i=0; i<relationList.size(); i++) {
					if (relationList.get(i).getId().equals(rid)) {
						relationList.set(i, relation);
						break;
					}
				}
				mediclaim.setRelationList(relationList);
			} 
			return ResponseEntity.ok(mediclaimRepository.save(mediclaim));
		}
	}
	
	
	@DeleteMapping("/{id}/delete-relation/{rid}")
	public ResponseEntity<Mediclaim> deleteRelation(@PathVariable("id") String id, @PathVariable("rid") String rid) {
		if (!mediclaimRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		} else {
			Mediclaim mediclaim = mediclaimRepository.findById(id).get();
			mediclaim.setModified(Instant.now().toEpochMilli());
			List<Relation> relationList = mediclaim.getRelationList();
			if (relationList != null) {
				for (int i=0; i<relationList.size(); i++) {
					if (relationList.get(i).getId().equals(rid)) {
						relationList.remove(i);
						break;
					}
				}
				mediclaim.setRelationList(relationList);
			} 
			return ResponseEntity.ok(mediclaimRepository.save(mediclaim));
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
