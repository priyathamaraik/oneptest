package com.prodapt.app.onboardingwebserver.controllers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prodapt.app.onboardingwebserver.dto.BulkCandidate;
import com.prodapt.app.onboardingwebserver.entities.Candidate;
import com.prodapt.app.onboardingwebserver.entities.ContactSpoc;
import com.prodapt.app.onboardingwebserver.entities.Email;
import com.prodapt.app.onboardingwebserver.entities.LandingPage;
import com.prodapt.app.onboardingwebserver.repository.CandidateRepository;
import com.prodapt.app.onboardingwebserver.repository.LandingPageRepository;
import com.prodapt.app.onboardingwebserver.services.EmailSenderService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/candidate")
public class CandidateController {

	Logger logger = LoggerFactory.getLogger(CandidateController.class);

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private EmailSenderService emailSenderService;
	
	@Autowired
	private LandingPageRepository landingPageRepository;
	

	@PostMapping("/register")
	public ResponseEntity<Candidate> registerCandidate(@Valid @RequestBody Candidate candidate) throws Exception {
		Optional<Candidate> cand = candidateRepository.findByEmail(candidate.getEmail());
		if (!cand.isPresent()) {
			logger.info("Candidate Saved");
			candidate.setPassword(bCryptPasswordEncoder.encode(candidate.getPassword()));
			return ResponseEntity.ok(candidateRepository.save(candidate));
		} else {
			logger.info("Candidate already exists, POST operation cannot be performed");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(candidate);
		}
	}

	@PostMapping("/bulkregister")
	public ResponseEntity<Iterable<Candidate>> bulkRegisterCandidate(@Valid @RequestBody String candidate) throws Exception {
		// logger.info(">>>>>>"+String.valueOf(candidate.size()));

		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		TypeReference<List<BulkCandidate>> typeRef = new TypeReference<List<BulkCandidate>>() {
		};
		List<BulkCandidate> map = mapper.readValue(candidate, typeRef);
		List<Candidate> candidateBulkList = new ArrayList<Candidate>();
		map.forEach(bulkList -> {
			Candidate candidateObj = new Candidate();
			candidateObj.setName(bulkList.getCandidateName());
			candidateObj.setEmail(bulkList.getCandidateEmail());
			candidateObj.setDateOfJoining(bulkList.getDateOfJoining().getTime());
			candidateObj.setHighestQualification(bulkList.getHighestQualification());
			candidateObj.setModeOfRecruitment(bulkList.getModeOfEmployment());
			candidateObj.setDepartment(bulkList.getDepartment());
			candidateObj.setCategory(bulkList.getCategory());
			candidateObj.setRegion(bulkList.getRegion());
			candidateObj.setCountry(bulkList.getCountry());
			Iterable<LandingPage> landingPage = landingPageRepository.findAll();
			List<ContactSpoc> hrbpList = new  ArrayList<ContactSpoc>();
			List<ContactSpoc> preBapList = new  ArrayList<ContactSpoc>();
			landingPage.forEach(landing ->{
				hrbpList.addAll(landing.getHrbpSpocList());
				preBapList.addAll(landing.getProboardingAdvisorList());
			});
			Optional<ContactSpoc> contactHrbpSpoc =  hrbpList.stream().filter(h->h.getName().equalsIgnoreCase(bulkList.gethRBPName())).findFirst();
			if(contactHrbpSpoc.isPresent())
			candidateObj.setHrbpSpoc(contactHrbpSpoc.get());
			Optional<ContactSpoc> contactPreBapSpoc =   preBapList.stream().filter(h->h.getName().equalsIgnoreCase(bulkList.getPreBAPAdvisorName())).findFirst();
			if(contactPreBapSpoc.isPresent())
			candidateObj.setProAdvisorSpoc(contactPreBapSpoc.get());
			candidateObj.setReportingManagerName(bulkList.getReportingManagerName());
			candidateObj.setReportingManageremail(bulkList.getReportingManagerEmail());
			candidateObj.setPracticeTag(bulkList.getPracticeTag());
			double k = Math.floor(100000 + Math.random() * 900000);
			logger.info(">>>>>>>Password"+String.valueOf((int)k));
			candidateObj.setPassword(bCryptPasswordEncoder.encode(String.valueOf((int)k)));
			candidateBulkList.add(candidateObj);
			Email email = new Email();
			email.setToEmail(bulkList.getCandidateEmail());
			String subject = "Pre BAP Initiation of " + bulkList.getCandidateName()
		            + "at Prodapt - Login Credentials";
			email.setSubject(subject);
			String body =  "\n\rLink: http://192.168.20.187:6376" + " ,"
		            + "\n\rThe login credentials are furnished below,"
		            + "\n\rUsername : " + bulkList.getCandidateEmail() + ','
		            + "\n\rPassword : " + String.valueOf((int)k);
			email.setBody(body);
			
			try {
				emailSenderService.sendWelcomeEmail(email);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("Sent Welcome email");

		});
		

		logger.info(">>>>>>" + map.size() + ">>>>" + map.get(0).getCandidateEmail() + ">>>>" + candidateBulkList.size());

		return ResponseEntity.ok(candidateRepository.saveAll(candidateBulkList));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Candidate> updateCandidate(@PathVariable("id") String id,
			@Valid @RequestBody Candidate candidate) throws Exception {
		if (candidateRepository.existsById(id)) {
			logger.info("Candidate Updated");
			candidate.setModified(Instant.now().toEpochMilli());
			return ResponseEntity.ok(candidateRepository.save(candidate));
		} else {
			logger.info("Candidate doesn't exist, PUT operation cannot be performed");
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/update-password/{id}")
	public ResponseEntity<Candidate> updateCandidatePassword(@PathVariable("id") String id,
			@Valid @RequestBody Candidate candidate) throws Exception {
		if (candidateRepository.existsById(id)) {
			logger.info("Candidate Password Updated");
			candidate.setModified(Instant.now().toEpochMilli());
			candidate.setPassword(bCryptPasswordEncoder.encode(candidate.getPassword()));
			return ResponseEntity.ok(candidateRepository.save(candidate));
		} else {
			logger.info("Candidate doesn't exist, PUT operation cannot be performed");
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<Candidate> deleteCandidate(@PathVariable("id") String id) throws Exception {
		if (candidateRepository.existsById(id)) {
			logger.info("Candidate Deleted");
			candidateRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			logger.info("Candidate doesn't exist, DELETE operation cannot be performed");
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("get/all")
	public ResponseEntity<Page<Candidate>> getAllCandidates(
			@PageableDefault(size = 200, sort = "created", direction = Direction.DESC) Pageable pageable)
			throws Exception {
		return ResponseEntity.ok(candidateRepository.findAll(pageable));
	}

	@GetMapping("get/{id}")
	public ResponseEntity<Optional<Candidate>> getCandidateById(@PathVariable("id") String id) throws Exception {
		return ResponseEntity.ok(candidateRepository.findById(id));
	}

	@GetMapping("get/email/{email}")
	public ResponseEntity<Optional<Candidate>> getCandidateByEmail(@PathVariable("email") String email)
			throws Exception {
		Optional<Candidate> candidate = candidateRepository.findByEmail(email);
		return ResponseEntity.ok(candidate);
	}

	@GetMapping("get/name/{name}")
	public ResponseEntity<Optional<Iterable<Candidate>>> getCandidateByName(@PathVariable("name") String name)
			throws Exception {
		return ResponseEntity.ok(candidateRepository.findByName(name));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException e) throws Exception {
		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}
