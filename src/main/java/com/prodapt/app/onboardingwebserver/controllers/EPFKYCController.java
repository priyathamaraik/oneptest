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

import com.prodapt.app.onboardingwebserver.entities.EPFKYCDetails;
import com.prodapt.app.onboardingwebserver.entities.KYCDetail;
import com.prodapt.app.onboardingwebserver.repository.EPFKYCRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/epf-kyc")
public class EPFKYCController {
	
	Logger logger = LoggerFactory.getLogger(EPFKYCController.class); 

	@Autowired
	private EPFKYCRepository epfkycRepository;
	
	@PostMapping("/save")
	public ResponseEntity<EPFKYCDetails> save(@Valid @RequestBody EPFKYCDetails epfkycDetails) {
		if (epfkycRepository.findByEmpId(epfkycDetails.getEmpId()).isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(epfkycRepository.findByEmpId(epfkycDetails.getEmpId()).get());
		} else {
			return ResponseEntity.ok(epfkycRepository.save(epfkycDetails));
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<EPFKYCDetails> update(@PathVariable("id") String id, @Valid @RequestBody EPFKYCDetails epfkycDetails) {
		if (epfkycRepository.existsById(id)) {
			epfkycDetails.setModifiedOn(Instant.now().toEpochMilli());
			return ResponseEntity.ok(epfkycRepository.save(epfkycDetails));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<EPFKYCDetails> delete(@PathVariable("id") String id) {
		if (epfkycRepository.existsById(id)) {
			epfkycRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/get/all")
	public ResponseEntity<Iterable<EPFKYCDetails>> findAll() {
		return ResponseEntity.ok(epfkycRepository.findAll());
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Optional<EPFKYCDetails>> findById(@PathVariable("id") String id) {
		return ResponseEntity.ok(epfkycRepository.findById(id));
	}
	
	@GetMapping("/get/empid/{empid}")
	public ResponseEntity<Optional<EPFKYCDetails>> findByEmpId(@PathVariable("empid") String empId){
		return ResponseEntity.ok(epfkycRepository.findByEmpId(empId));
	}
	
	@PostMapping("/{id}/save-kyc-detail")
	public ResponseEntity<EPFKYCDetails> saveKYCDetail(@PathVariable("id") String id, @Valid @RequestBody KYCDetail kycDetail) {
		if (epfkycRepository.existsById(id)) {
			EPFKYCDetails epfkycDetails = epfkycRepository.findById(id).get();
			List<KYCDetail> kydDetailList = new ArrayList<KYCDetail>();
			if (epfkycDetails.getKycDetailList() == null ) {
				kydDetailList.add(kycDetail);
			} else {
				kydDetailList.addAll(epfkycDetails.getKycDetailList());
				kydDetailList.add(kycDetail);
			}
			epfkycDetails.setKycDetailList(kydDetailList);
			return ResponseEntity.ok(epfkycRepository.save(epfkycDetails));	
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}/update-kyc-detail/{kycId}")
	public ResponseEntity<EPFKYCDetails> updateKYCDetail(@PathVariable("id") String id, @PathVariable("kycId") String kycId, @Valid @RequestBody KYCDetail kycDetail) {
		if (epfkycRepository.existsById(id)) {
			EPFKYCDetails epfkycDetails = epfkycRepository.findById(id).get();
			List<KYCDetail> nomineeList = epfkycDetails.getKycDetailList();
			if (nomineeList != null) {
				for (int i=0; i<nomineeList.size(); i++) {
					if (nomineeList.get(i).getId().equals(kycId)) {
						nomineeList.set(i, kycDetail);
						break;
					}
				}
				epfkycDetails.setKycDetailList(nomineeList);
			}
			return ResponseEntity.ok(epfkycRepository.save(epfkycDetails));
			
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}/delete-kyc-detail/{kycId}")
	public ResponseEntity<EPFKYCDetails> deleteKYCDetail(@PathVariable("id") String id, @PathVariable("kycId") String kycId) {
		if (epfkycRepository.existsById(id)) {
			EPFKYCDetails epfkycDetails = epfkycRepository.findById(id).get();
			List<KYCDetail> addressList = epfkycDetails.getKycDetailList();
			if (addressList != null) {
				for (int i=0; i<addressList.size(); i++) {
					if (addressList.get(i).getId().equals(kycId)) {
						addressList.remove(i);
						break;
					}
				}
				epfkycDetails.setKycDetailList(addressList);
			}
			return ResponseEntity.ok(epfkycRepository.save(epfkycDetails));
			
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
