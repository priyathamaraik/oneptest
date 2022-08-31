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

import com.prodapt.app.onboardingwebserver.entities.IdCard;
import com.prodapt.app.onboardingwebserver.entities.Vehicle;
import com.prodapt.app.onboardingwebserver.repository.IdCardRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/idcard")
public class IdCardController {

	@Autowired
	private IdCardRepository idCardRepository;

	@PostMapping("/save")
	public ResponseEntity<IdCard> save(@RequestBody @Valid IdCard idCard) {
		if (idCardRepository.findByEmpId(idCard.getEmpId()).isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(idCard);
		} else {
			return ResponseEntity.ok(idCardRepository.save(idCard));	
		}
	}

	
	@PutMapping("/update/{id}")
	public ResponseEntity<IdCard> update(@PathVariable("id") String id, @Valid @RequestBody IdCard idCard) {
		if (idCardRepository.existsById(id)) {
			idCard.setModified(Instant.now().toEpochMilli());
			return ResponseEntity.ok(idCardRepository.save(idCard));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@GetMapping("/get/empid/{empId}")
	public ResponseEntity<Optional<IdCard>> getByEmpId(@PathVariable("empId") String empId){
		Optional<IdCard> idCard = idCardRepository.findByEmpId(empId);  
		return ResponseEntity.ok(idCard);
	}

	
	@GetMapping("/get/all")
	public ResponseEntity<Iterable<IdCard>> getAllIdCards() {
		return ResponseEntity.ok(idCardRepository.findAll());
	}

	
	@GetMapping("/get/{id}")
	public ResponseEntity<Optional<IdCard>> findIdCardById(@PathVariable("id") String id) {
		return ResponseEntity.ok(idCardRepository.findById(id));
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<IdCard> delete(@PathVariable("id") String id) {
		if (idCardRepository.existsById(id)) {
			idCardRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	
	@PostMapping("/{id}/save-vehicle")
	public ResponseEntity<IdCard> saveVehicleDetails(@PathVariable("id") String id, @Valid @RequestBody Vehicle vehicle) {
		Optional<IdCard> idCard = idCardRepository.findById(id);
		if (!idCard.isPresent()) {
			return ResponseEntity.notFound().build();
		} else {
			IdCard idc = idCard.get();
			idc.setModified(Instant.now().toEpochMilli());
			List<Vehicle> vehicleList = new ArrayList<>();
			if (idc.getVehicleDetails() == null) {
				vehicleList.add(vehicle);
			} else {
				vehicleList.addAll(idc.getVehicleDetails());
				vehicleList.add(vehicle);
			}
			idc.setVehicleDetails(vehicleList);
			return ResponseEntity.ok(idCardRepository.save(idc));
		}
	}

	
	@PutMapping("/{id}/update-vehicle/{vhId}")
	public ResponseEntity<IdCard> updateVehicleDetails(@PathVariable("id") String id,
			@PathVariable("vhId") String vehicleId, @Valid @RequestBody Vehicle vehicle) {

		if (!idCardRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		} else {
			Optional<IdCard> idCard = idCardRepository.findById(id);
			IdCard idc = idCard.get();
			idc.setModified(Instant.now().toEpochMilli());
			List<Vehicle> vehicleList = new ArrayList<>();
			if (idc.getVehicleDetails() != null) {
				vehicleList.addAll(idc.getVehicleDetails());
			}
			for (int i = 0; i < vehicleList.size(); i++) {
				if (vehicleList.get(i).getId().equals(vehicleId)) {
					vehicleList.set(i, vehicle);
					break;
				}
			}
			idc.setVehicleDetails(vehicleList);
			return ResponseEntity.ok(idCardRepository.save(idc));
		}
	}

	
	@DeleteMapping("/{id}/delete-vehicle/{vhId}")
	public ResponseEntity<IdCard> deleteVehicleDetails(@PathVariable("id") String id,
			@PathVariable("vhId") String vehicleId) {

		if (!idCardRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		} else {
			Optional<IdCard> idCard = idCardRepository.findById(id);
			IdCard idc = idCard.get();
			idc.setModified(Instant.now().toEpochMilli());
			List<Vehicle> vehicleList = new ArrayList<>();
			if (idc.getVehicleDetails() != null) {
				vehicleList.addAll(idc.getVehicleDetails());
			}
			for (int i = 0; i < vehicleList.size(); i++) {
				if (vehicleList.get(i).getId().equals(vehicleId)) {
					vehicleList.remove(i);
					break;
				}
			}
			idc.setVehicleDetails(vehicleList);
			return ResponseEntity.ok(idCardRepository.save(idc));
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
