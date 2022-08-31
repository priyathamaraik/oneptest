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

import com.prodapt.app.onboardingwebserver.entities.Address;
import com.prodapt.app.onboardingwebserver.entities.CandidateDetails;
import com.prodapt.app.onboardingwebserver.entities.Education;
import com.prodapt.app.onboardingwebserver.entities.Identity;
import com.prodapt.app.onboardingwebserver.entities.Language;
import com.prodapt.app.onboardingwebserver.entities.RefPerson;
import com.prodapt.app.onboardingwebserver.entities.Specialization;
import com.prodapt.app.onboardingwebserver.entities.Travel;
import com.prodapt.app.onboardingwebserver.entities.Work;
import com.prodapt.app.onboardingwebserver.repository.CandidateDetailsRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/candidate-details")
public class CandidateDetailsController {
	
	Logger logger = LoggerFactory.getLogger(CandidateDetailsController.class);

	@Autowired
	private CandidateDetailsRepository candidateDetailsRepository;

	@PostMapping("/save")
	public ResponseEntity<CandidateDetails> save(@Valid @RequestBody CandidateDetails candidateDetails) {
		if (candidateDetailsRepository.findByEmpId(candidateDetails.getEmpId()).isPresent()) {
			logger.error("Candidate Details already exists, POST operation cannot be performed");
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(candidateDetailsRepository.findByEmpId(candidateDetails.getEmpId()).get());
		} else {
			logger.info("Candidate Details Saved");
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<CandidateDetails> update(@PathVariable("id") String id,
			@Valid @RequestBody CandidateDetails candidateDetails) {
		if (candidateDetailsRepository.existsById(id)) {
			logger.error("Candidate Details already exists, PUT operation cannot be performed");
			candidateDetails.setModified(Instant.now().toEpochMilli());
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));
		} else {
			logger.info("Candidate Details Saved");
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/approve/{id}/{hrEmail}")
	public ResponseEntity<CandidateDetails> approveCandidate(@PathVariable("id") String id,
			@PathVariable("hrEmail") String hrEmail) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			candidateDetails.setModified(Instant.now().toEpochMilli());
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));
		} else {
			logger.error("Candidate Details already exists, PUT operation cannot be performed");
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CandidateDetails> delete(@PathVariable("id") String id) {
		if (candidateDetailsRepository.existsById(id)) {
			candidateDetailsRepository.deleteById(id);
			logger.info("Candidate Details Deleted");
			return ResponseEntity.noContent().build();
		} else {
			logger.error("Candidate Details doesn't exist, DELETE operation cannot be performed");
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("get/all")
	public ResponseEntity<Iterable<CandidateDetails>> getAll() {
		return ResponseEntity.ok(candidateDetailsRepository.findAll());
	}

	@GetMapping("get/{id}")
	public ResponseEntity<Optional<CandidateDetails>> getById(@PathVariable("id") String id) {
		return ResponseEntity.ok(candidateDetailsRepository.findById(id));
	}

	@GetMapping("get/empid/{empId}")
	public ResponseEntity<Optional<CandidateDetails>> getByEmpId(@PathVariable("empId") String empId) {
		return ResponseEntity.ok(candidateDetailsRepository.findByEmpId(empId));
	}

	@GetMapping("get/email/{email}")
	public ResponseEntity<Optional<CandidateDetails>> getByEmail(@PathVariable("email") String email) {
		return ResponseEntity.ok(candidateDetailsRepository.findByemail(email));
	}

	@GetMapping("get/bloodgroup/{bloodGroup}")
	public ResponseEntity<Optional<Iterable<CandidateDetails>>> getByBloodGroup(
			@PathVariable("bloodGroup") String bloodGroup) {
		return ResponseEntity.ok(candidateDetailsRepository.findByBloodGroup(bloodGroup));
	}

	@PostMapping("/{id}/save-address")
	public ResponseEntity<CandidateDetails> saveAddress(@PathVariable("id") String id,
			@Valid @RequestBody Address address) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Address> addressList = new ArrayList<Address>();
			if (candidateDetails.getAddressList() == null) {
				addressList.add(address);
			} else {
				addressList.addAll(candidateDetails.getAddressList());
				addressList.add(address);
			}
			candidateDetails.setAddressList(addressList);
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}/get-address/{addrId}")
	public ResponseEntity<Address> getAddress(@PathVariable("id") String id, @PathVariable("addrId") String addrId) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Address> refPersonList = candidateDetails.getAddressList();
			if (refPersonList != null) {
				for (int i = 0; i < refPersonList.size(); i++) {
					if (refPersonList.get(i).getId().equals(addrId)) {
						return ResponseEntity.ok(refPersonList.get(i));
					}
				}
			}
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}/update-address/{addrId}")
	public ResponseEntity<CandidateDetails> updateAddress(@PathVariable("id") String id,
			@PathVariable("addrId") String addrId, @Valid @RequestBody Address address) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Address> addressList = candidateDetails.getAddressList();
			if (addressList != null) {
				for (int i = 0; i < addressList.size(); i++) {
					if (addressList.get(i).getId().equals(addrId)) {
						addressList.set(i, address);
						break;
					}
				}
				candidateDetails.setAddressList(addressList);
			}
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}/delete-address/{addrId}")
	public ResponseEntity<CandidateDetails> deleteAddress(@PathVariable("id") String id,
			@PathVariable("addrId") String addrId) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Address> addressList = candidateDetails.getAddressList();
			if (addressList != null) {
				for (int i = 0; i < addressList.size(); i++) {
					if (addressList.get(i).getId().equals(addrId)) {
						addressList.remove(i);
						break;
					}
				}
				candidateDetails.setAddressList(addressList);
			}
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/{id}/save-identity")
	public ResponseEntity<CandidateDetails> saveIdentity(@PathVariable("id") String id,
			@Valid @RequestBody Identity identity) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Identity> identityList = new ArrayList<Identity>();
			if (candidateDetails.getIdentityList() == null) {
				identityList.add(identity);
			} else {
				identityList.addAll(candidateDetails.getIdentityList());
				identityList.add(identity);
			}
			candidateDetails.setIdentityList(identityList);
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}/get-identity/{idenId}")
	public ResponseEntity<Identity> getIdentity(@PathVariable("id") String id, @PathVariable("idenId") String idenId) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Identity> refPersonList = candidateDetails.getIdentityList();
			if (refPersonList != null) {
				for (int i = 0; i < refPersonList.size(); i++) {
					if (refPersonList.get(i).getId().equals(idenId)) {
						return ResponseEntity.ok(refPersonList.get(i));
					}
				}
			}
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}/update-identity/{idenId}")
	public ResponseEntity<CandidateDetails> updateIdentity(@PathVariable("id") String id,
			@PathVariable("idenId") String addrId, @Valid @RequestBody Identity identity) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Identity> addressList = candidateDetails.getIdentityList();
			if (addressList != null) {
				for (int i = 0; i < addressList.size(); i++) {
					if (addressList.get(i).getId().equals(addrId)) {
						addressList.set(i, identity);
						break;
					}
				}
				candidateDetails.setIdentityList(addressList);
			}
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}/delete-identity/{idenId}")
	public ResponseEntity<CandidateDetails> deleteIdentity(@PathVariable("id") String id,
			@PathVariable("idenId") String idenId) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Identity> addressList = candidateDetails.getIdentityList();
			if (addressList != null) {
				for (int i = 0; i < addressList.size(); i++) {
					if (addressList.get(i).getId().equals(idenId)) {
						addressList.remove(i);
						break;
					}
				}
				candidateDetails.setIdentityList(addressList);
			}
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/{id}/save-language")
	public ResponseEntity<CandidateDetails> saveLanguage(@PathVariable("id") String id,
			@Valid @RequestBody Language language) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Language> languageList = new ArrayList<Language>();
			if (candidateDetails.getLanguageList() == null) {
				languageList.add(language);
			} else {
				languageList.addAll(candidateDetails.getLanguageList());
				languageList.add(language);
			}
			candidateDetails.setLanguageList(languageList);
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}/get-language/{langId}")
	public ResponseEntity<Language> getLanguage(@PathVariable("id") String id, @PathVariable("langId") String langId) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Language> refPersonList = candidateDetails.getLanguageList();
			if (refPersonList != null) {
				for (int i = 0; i < refPersonList.size(); i++) {
					if (refPersonList.get(i).getId().equals(langId)) {
						return ResponseEntity.ok(refPersonList.get(i));
					}
				}
			}
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}/update-language/{langId}")
	public ResponseEntity<CandidateDetails> updateLanguage(@PathVariable("id") String id,
			@PathVariable("langId") String langId, @Valid @RequestBody Language language) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Language> addressList = candidateDetails.getLanguageList();
			if (addressList != null) {
				for (int i = 0; i < addressList.size(); i++) {
					if (addressList.get(i).getId().equals(langId)) {
						addressList.set(i, language);
						break;
					}
				}
				candidateDetails.setLanguageList(addressList);
			}
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}/delete-language/{langId}")
	public ResponseEntity<CandidateDetails> deleteLanguage(@PathVariable("id") String id,
			@PathVariable("langId") String langId) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Language> addressList = candidateDetails.getLanguageList();
			if (addressList != null) {
				for (int i = 0; i < addressList.size(); i++) {
					if (addressList.get(i).getId().equals(langId)) {
						addressList.remove(i);
						break;
					}
				}
				candidateDetails.setLanguageList(addressList);
			}
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/{id}/save-education")
	public ResponseEntity<CandidateDetails> saveEducation(@PathVariable("id") String id,
			@Valid @RequestBody Education education) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Education> educationList = new ArrayList<Education>();
			if (candidateDetails.getEducationList() == null) {
				educationList.add(education);
			} else {
				educationList.addAll(candidateDetails.getEducationList());
				educationList.add(education);
			}
			candidateDetails.setEducationList(educationList);
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}/get-education/{eduId}")
	public ResponseEntity<Education> getEducation(@PathVariable("id") String id, @PathVariable("eduId") String eduId) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Education> educationList = candidateDetails.getEducationList();
			if (educationList != null) {
				for (int i = 0; i < educationList.size(); i++) {
					if (educationList.get(i).getId().equals(eduId)) {
						return ResponseEntity.ok(educationList.get(i));
					}
				}
			}
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	

	@PutMapping("/{id}/update-education/{eduId}")
	public ResponseEntity<CandidateDetails> updateEducation(@PathVariable("id") String id,
			@PathVariable("eduId") String eduId, @Valid @RequestBody Education education) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Education> addressList = candidateDetails.getEducationList();
			if (addressList != null) {
				for (int i = 0; i < addressList.size(); i++) {
					if (addressList.get(i).getId().equals(eduId)) {
						addressList.set(i, education);
						break;
					}
				}
				candidateDetails.setEducationList(addressList);
			}
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}/delete-education/{eduId}")
	public ResponseEntity<CandidateDetails> deleteEducation(@PathVariable("id") String id,
			@PathVariable("eduId") String eduId) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Education> addressList = candidateDetails.getEducationList();
			if (addressList != null) {
				for (int i = 0; i < addressList.size(); i++) {
					if (addressList.get(i).getId().equals(eduId)) {
						addressList.remove(i);
						break;
					}
				}
				candidateDetails.setEducationList(addressList);
			}
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/{id}/save-work")
	public ResponseEntity<CandidateDetails> saveWork(@PathVariable("id") String id, @Valid @RequestBody Work work) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Work> workList = new ArrayList<Work>();
			if (candidateDetails.getWorkList() == null) {
				workList.add(work);
			} else {
				workList.addAll(candidateDetails.getWorkList());
				workList.add(work);
			}
			candidateDetails.setWorkList(workList);
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}/get-work/{workId}")
	public ResponseEntity<Work> getWork(@PathVariable("id") String id, @PathVariable("workId") String workId) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Work> workList = candidateDetails.getWorkList();
			if (workList != null) {
				for (int i = 0; i < workList.size(); i++) {
					if (workList.get(i).getId().equals(workId)) {
						return ResponseEntity.ok(workList.get(i));
					}
				}
			}
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}/update-work/{workId}")
	public ResponseEntity<CandidateDetails> updateWork(@PathVariable("id") String id,
			@PathVariable("workId") String eduId, @Valid @RequestBody Work work) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Work> addressList = candidateDetails.getWorkList();
			if (addressList != null) {
				for (int i = 0; i < addressList.size(); i++) {
					if (addressList.get(i).getId().equals(eduId)) {
						addressList.set(i, work);
						break;
					}
				}
				candidateDetails.setWorkList(addressList);
			}
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}/delete-work/{workId}")
	public ResponseEntity<CandidateDetails> deleteWork(@PathVariable("id") String id,
			@PathVariable("workId") String workId) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Work> addressList = candidateDetails.getWorkList();
			if (addressList != null) {
				for (int i = 0; i < addressList.size(); i++) {
					if (addressList.get(i).getId().equals(workId)) {
						addressList.remove(i);
						break;
					}
				}
				candidateDetails.setWorkList(addressList);
			}
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));

		} else {
			return ResponseEntity.notFound().build();
		}
	}

//	@PostMapping("/{id}/save-family")
//	public ResponseEntity<CandidateDetails> saveFamily(@PathVariable("id") String id,
//			@Valid @RequestBody Family family) {
//		if (candidateDetailsRepository.existsById(id)) {
//			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
//			List<Family> familyList = new ArrayList<Family>();
//			if (candidateDetails.getFamilyList() == null) {
//				familyList.add(family);
//			} else {
//				familyList.addAll(candidateDetails.getFamilyList());
//				familyList.add(family);
//			}
//			candidateDetails.setFamilyList(familyList);
//			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));
//
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}
//
//	@GetMapping("/{id}/get-family/{famId}")
//	public ResponseEntity<Family> getFamily(@PathVariable("id") String id, @PathVariable("famId") String famId) {
//		if (candidateDetailsRepository.existsById(id)) {
//			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
//			List<Family> familyList = candidateDetails.getFamilyList();
//			if (familyList != null) {
//				for (int i = 0; i < familyList.size(); i++) {
//					if (familyList.get(i).getId().equals(famId)) {
//						return ResponseEntity.ok(familyList.get(i));
//					}
//				}
//			}
//			return ResponseEntity.notFound().build();
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}
//
//	@PutMapping("/{id}/update-family/{famId}")
//	public ResponseEntity<CandidateDetails> updateFamily(@PathVariable("id") String id,
//			@PathVariable("famId") String famId, @Valid @RequestBody Family family) {
//		if (candidateDetailsRepository.existsById(id)) {
//			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
//			List<Family> addressList = candidateDetails.getFamilyList();
//			if (addressList != null) {
//				for (int i = 0; i < addressList.size(); i++) {
//					if (addressList.get(i).getId().equals(famId)) {
//						addressList.set(i, family);
//						break;
//					}
//				}
//				candidateDetails.setFamilyList(addressList);
//			}
//			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));
//
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}
//
//	@DeleteMapping("/{id}/delete-family/{famId}")
//	public ResponseEntity<CandidateDetails> deleteFamily(@PathVariable("id") String id,
//			@PathVariable("famId") String famId) {
//		if (candidateDetailsRepository.existsById(id)) {
//			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
//			List<Family> addressList = candidateDetails.getFamilyList();
//			if (addressList != null) {
//				for (int i = 0; i < addressList.size(); i++) {
//					if (addressList.get(i).getId().equals(famId)) {
//						addressList.remove(i);
//						break;
//					}
//				}
//				candidateDetails.setFamilyList(addressList);
//			}
//			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));
//
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}

	@PostMapping("/{id}/save-travel")
	public ResponseEntity<CandidateDetails> saveTravel(@PathVariable("id") String id,
			@Valid @RequestBody Travel travel) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Travel> travelList = new ArrayList<Travel>();
			if (candidateDetails.getTravelList() == null) {
				travelList.add(travel);
			} else {
				travelList.addAll(candidateDetails.getTravelList());
				travelList.add(travel);
			}
			candidateDetails.setTravelList(travelList);
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}/get-travel/{travelId}")
	public ResponseEntity<Travel> getTravel(@PathVariable("id") String id, @PathVariable("travelId") String travelId) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Travel> travelList = candidateDetails.getTravelList();
			if (travelList != null) {
				for (int i = 0; i < travelList.size(); i++) {
					if (travelList.get(i).getId().equals(travelId)) {
						return ResponseEntity.ok(travelList.get(i));
					}
				}
			}
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}/update-travel/{travelId}")
	public ResponseEntity<CandidateDetails> updateTravel(@PathVariable("id") String id,
			@PathVariable("travelId") String travelId, @Valid @RequestBody Travel travel) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Travel> addressList = candidateDetails.getTravelList();
			if (addressList != null) {
				for (int i = 0; i < addressList.size(); i++) {
					if (addressList.get(i).getId().equals(travelId)) {
						addressList.set(i, travel);
						break;
					}
				}
				candidateDetails.setTravelList(addressList);
			}
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}/delete-travel/{travelId}")
	public ResponseEntity<CandidateDetails> deleteTravel(@PathVariable("id") String id,
			@PathVariable("travelId") String travelId) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Travel> addressList = candidateDetails.getTravelList();
			if (addressList != null) {
				for (int i = 0; i < addressList.size(); i++) {
					if (addressList.get(i).getId().equals(travelId)) {
						addressList.remove(i);
						break;
					}
				}
				candidateDetails.setTravelList(addressList);
			}
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/{id}/save-specialization")
	public ResponseEntity<CandidateDetails> saveSpecilization(@PathVariable("id") String id,
			@Valid @RequestBody Specialization specialization) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Specialization> specializationList = new ArrayList<Specialization>();
			if (candidateDetails.getSpecializationList() == null) {
				specializationList.add(specialization);
			} else {
				specializationList.addAll(candidateDetails.getSpecializationList());
				specializationList.add(specialization);
			}
			candidateDetails.setSpecializationList(specializationList);
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}/get-specialization/{splId}")
	public ResponseEntity<Specialization> getSpecialization(@PathVariable("id") String id,
			@PathVariable("splId") String splId) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Specialization> specializationList = candidateDetails.getSpecializationList();
			if (specializationList != null) {
				for (int i = 0; i < specializationList.size(); i++) {
					if (specializationList.get(i).getId().equals(splId)) {
						return ResponseEntity.ok(specializationList.get(i));
					}
				}
			}
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}/update-specialization/{splId}")
	public ResponseEntity<CandidateDetails> updateSpecialization(@PathVariable("id") String id,
			@PathVariable("splId") String splId, @Valid @RequestBody Specialization specialization) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Specialization> addressList = candidateDetails.getSpecializationList();
			if (addressList != null) {
				for (int i = 0; i < addressList.size(); i++) {
					if (addressList.get(i).getId().equals(splId)) {
						addressList.set(i, specialization);
						break;
					}
				}
				candidateDetails.setSpecializationList(addressList);
			}
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}/delete-specialization/{splId}")
	public ResponseEntity<CandidateDetails> deleteSpecialization(@PathVariable("id") String id,
			@PathVariable("splId") String splId) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<Specialization> addressList = candidateDetails.getSpecializationList();
			if (addressList != null) {
				for (int i = 0; i < addressList.size(); i++) {
					if (addressList.get(i).getId().equals(splId)) {
						addressList.remove(i);
						break;
					}
				}
				candidateDetails.setSpecializationList(addressList);
			}
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

//	@PostMapping("/{id}/save-certifications")
//	public ResponseEntity<CandidateDetails> saveCertifications(@PathVariable("id") String id,
//			@Valid @RequestBody Certifications certifications) {
//		if (candidateDetailsRepository.existsById(id)) {
//			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
//			List<Certifications> certificationsList = new ArrayList<Certifications>();
//			if (candidateDetails.getCertificationsList() == null) {
//				certificationsList.add(certifications);
//			} else {
//				certificationsList.addAll(candidateDetails.getCertificationsList());
//				certificationsList.add(certifications);
//			}
//			candidateDetails.setCertificationsList(certificationsList);
//			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));
//
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}
//
//	@GetMapping("/{id}/get-certification/{certId}")
//	public ResponseEntity<Certifications> getCertification(@PathVariable("id") String id,
//			@PathVariable("certId") String certId) {
//		if (candidateDetailsRepository.existsById(id)) {
//			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
//			List<Certifications> certificationList = candidateDetails.getCertificationsList();
//			if (certificationList != null) {
//				for (int i = 0; i < certificationList.size(); i++) {
//					if (certificationList.get(i).getId().equals(certId)) {
//						return ResponseEntity.ok(certificationList.get(i));
//					}
//				}
//			}
//			return ResponseEntity.notFound().build();
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}
//
//	@PutMapping("/{id}/update-certifications/{certId}")
//	public ResponseEntity<CandidateDetails> updateCertifications(@PathVariable("id") String id,
//			@PathVariable("certId") String splId, @Valid @RequestBody Certifications certifications) {
//		if (candidateDetailsRepository.existsById(id)) {
//			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
//			List<Certifications> addressList = candidateDetails.getCertificationsList();
//			if (addressList != null) {
//				for (int i = 0; i < addressList.size(); i++) {
//					if (addressList.get(i).getId().equals(splId)) {
//						addressList.set(i, certifications);
//						break;
//					}
//				}
//				candidateDetails.setCertificationsList(addressList);
//			}
//			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));
//
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}
//
//	@DeleteMapping("/{id}/delete-certifications/{certId}")
//	public ResponseEntity<CandidateDetails> deleteCertifications(@PathVariable("id") String id,
//			@PathVariable("certId") String certId) {
//		if (candidateDetailsRepository.existsById(id)) {
//			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
//			List<Certifications> addressList = candidateDetails.getCertificationsList();
//			if (addressList != null) {
//				for (int i = 0; i < addressList.size(); i++) {
//					if (addressList.get(i).getId().equals(certId)) {
//						addressList.remove(i);
//						break;
//					}
//				}
//				candidateDetails.setCertificationsList(addressList);
//			}
//			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}

	@PostMapping("/{id}/save-refperson")
	public ResponseEntity<CandidateDetails> saveRefPerson(@PathVariable("id") String id,
			@Valid @RequestBody RefPerson refPerson) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<RefPerson> certificationsList = new ArrayList<RefPerson>();
			if (candidateDetails.getRefPersonList() == null) {
				certificationsList.add(refPerson);
			} else {
				certificationsList.addAll(candidateDetails.getRefPersonList());
				certificationsList.add(refPerson);
			}
			candidateDetails.setRefPersonList(certificationsList);
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}/get-refperson/{refId}")
	public ResponseEntity<RefPerson> getRefPerson(@PathVariable("id") String id, @PathVariable("refId") String refId) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<RefPerson> refPersonList = candidateDetails.getRefPersonList();
			if (refPersonList != null) {
				for (int i = 0; i < refPersonList.size(); i++) {
					if (refPersonList.get(i).getId().equals(refId)) {
						return ResponseEntity.ok(refPersonList.get(i));
					}
				}
			}
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}/update-refperson/{refId}")
	public ResponseEntity<CandidateDetails> updateRefPerson(@PathVariable("id") String id,
			@PathVariable("refId") String refId, @Valid @RequestBody RefPerson refPerson) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<RefPerson> addressList = candidateDetails.getRefPersonList();
			if (addressList != null) {
				for (int i = 0; i < addressList.size(); i++) {
					if (addressList.get(i).getId().equals(refId)) {
						addressList.set(i, refPerson);
						break;
					}
				}
				candidateDetails.setRefPersonList(addressList);
			}
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}/delete-refperson/{refId}")
	public ResponseEntity<CandidateDetails> deleteRefPerson(@PathVariable("id") String id,
			@PathVariable("refId") String refId) {
		if (candidateDetailsRepository.existsById(id)) {
			CandidateDetails candidateDetails = candidateDetailsRepository.findById(id).get();
			List<RefPerson> addressList = candidateDetails.getRefPersonList();
			if (addressList != null) {
				for (int i = 0; i < addressList.size(); i++) {
					if (addressList.get(i).getId().equals(refId)) {
						addressList.remove(i);
						break;
					}
				}
				candidateDetails.setRefPersonList(addressList);
			}
			return ResponseEntity.ok(candidateDetailsRepository.save(candidateDetails));
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
