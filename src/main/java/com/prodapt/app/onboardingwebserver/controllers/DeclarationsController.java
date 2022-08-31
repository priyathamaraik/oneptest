package com.prodapt.app.onboardingwebserver.controllers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import com.prodapt.app.onboardingwebserver.entities.Declarations;
import com.prodapt.app.onboardingwebserver.entities.Document;
import com.prodapt.app.onboardingwebserver.repository.DeclarationsRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/declarations")
public class DeclarationsController {
		
	Logger logger = LoggerFactory.getLogger(DeclarationsController.class);
	
	@Autowired
	private DeclarationsRepository declarationsRepository;
	
	@PostMapping("/save")
	public ResponseEntity<Declarations> save(@Valid @RequestBody Declarations declarations) {
		if (declarationsRepository.findByEmpId(declarations.getEmpId()).isPresent()) {
			logger.error("Declarations already exists, POST operation cannot be performed");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(declarationsRepository.findByEmpId(declarations.getEmpId()).get());
		} else {
			logger.info("Declarations Saved");
			return ResponseEntity.ok(declarationsRepository.save(declarations));
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Declarations> update(@Valid @RequestBody Declarations declarations, @PathVariable("id") String id) {
		if (declarationsRepository.existsById(id)) {
			logger.info("Declarations Updated");
			declarations.setModifiedOn(Instant.now().toEpochMilli());
			return ResponseEntity.ok(declarationsRepository.save(declarations));
		} else {
			logger.error("Declarations doesn't exist, PUT operation cannot be performed");
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Declarations> delete(@PathVariable("id") String id) {
		if (declarationsRepository.existsById(id)) {
			declarationsRepository.deleteById(id);
			logger.info("Declarations Deleted");
			return ResponseEntity.noContent().build();
		} else {
			logger.error("Declarations doesn't exist, DELETE operation cannot be performed");
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/get/all")
	public ResponseEntity<Iterable<Declarations>> findAll() {
		return ResponseEntity.ok(declarationsRepository.findAll());
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Optional<Declarations>> findById(@PathVariable("id") String id) {
		return ResponseEntity.ok(declarationsRepository.findById(id));
	}
	
	@GetMapping("/get/empid/{empid}")
	public ResponseEntity<Optional<Declarations>> findByEmpId(@PathVariable("empid") String empId){
		return ResponseEntity.ok(declarationsRepository.findByEmpId(empId));
	}
	
	@GetMapping("get/emp-name/{emp-name}")
	public ResponseEntity<Optional<Iterable<Declarations>>> findByEmpName(@PathVariable("emp-name") String empName) {
		return ResponseEntity.ok(declarationsRepository.findByEmpName(empName));
	}
	
	@PostMapping("/{id}/save-document")
	public ResponseEntity<Declarations> saveDocument(@PathVariable("id") String id, @Valid @RequestBody Document document) {
		if (declarationsRepository.existsById(id)) {
			Declarations declarations = declarationsRepository.findById(id).get();
			List<Document> documentsList = new ArrayList<Document>();
			if (declarations.getDocumentsList() == null ) {
				documentsList.add(document);
			} else {
				documentsList.addAll(declarations.getDocumentsList());
				documentsList.add(document);
			}
			declarations.setDocumentsList(documentsList);
			return ResponseEntity.ok(declarationsRepository.save(declarations));	
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}/update-document/{docId}")
	public ResponseEntity<Declarations> updateDocument(@PathVariable("id") String id, @PathVariable("docId") String docId, @Valid @RequestBody Document document) {
		if (declarationsRepository.existsById(id)) {
			Declarations declarations = declarationsRepository.findById(id).get();
			List<Document> documents = declarations.getDocumentsList();
			if (documents != null) {
				for (int i=0; i<documents.size(); i++) {
					if (documents.get(i).getId().equals(docId)) {
						documents.set(i, document);
						break;
					}
				}
				declarations.setDocumentsList(documents);
			}
			return ResponseEntity.ok(declarationsRepository.save(declarations));
			
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}/delete-document/{docId}")
	public ResponseEntity<Declarations> deleteDocument(@PathVariable("id") String id, @PathVariable("docId") String docId) {
		if (declarationsRepository.existsById(id)) {
			Declarations declarations = declarationsRepository.findById(id).get();
			List<Document> documentsList = declarations.getDocumentsList();
			if (documentsList != null) {
				for (int i=0; i<documentsList.size(); i++) {
					if (documentsList.get(i).getId().equals(docId)) {
						documentsList.remove(i);
						break;
					}
				}
				declarations.setDocumentsList(documentsList);
			}
			return ResponseEntity.ok(declarationsRepository.save(declarations));
			
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
