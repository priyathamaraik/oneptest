package com.prodapt.app.onboardingwebserver.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prodapt.app.onboardingwebserver.entities.Email;
import com.prodapt.app.onboardingwebserver.services.EmailSenderService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/email")
public class EmailController {

	Logger logger = LoggerFactory.getLogger(EmailController.class);
	
	@Autowired
	private EmailSenderService emailSenderService;
	
	@PostMapping("/send-welcome")
	private ResponseEntity<Email> sendWelcomeEmail(@Valid @RequestBody Email email) throws Exception {
		emailSenderService.sendWelcomeEmail(email);
		logger.info("Sent Welcome email");
		return ResponseEntity.ok().build();	
	}
	
	@PostMapping("/send-general")
	private ResponseEntity<Email> sendGeneralEmail(@Valid @RequestBody Email email) throws Exception {
		emailSenderService.sendGeneralEmail(email);
		logger.info("Sent General email");
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/send-general-hr")
	private ResponseEntity<Email> sendGeneralEmailHr(@Valid @RequestBody Email email) throws Exception {
		emailSenderService.sendGeneralEmailHr(email);
		logger.info("Sent General email HR");
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/send-simple") 
	private ResponseEntity<Email> sendSimpleEmail(@Valid @RequestBody Email email) throws Exception {
		emailSenderService.sendSimpleEmail(email);
		logger.info("Sent simple email");
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/send-with-attachment")
	private ResponseEntity<Email> sendEmailWithAttachemnt(@Valid @RequestBody Email email) throws Exception {
		emailSenderService.sendEmailWithAttachment(email);
		logger.info("Sent email with attachment");
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/send-general-attachment")
	private ResponseEntity<Email> sendEmailTemplateWithAttachemnt(@Valid @RequestBody Email email) throws Exception {
		emailSenderService.sendEmailTemplateWithAttachemnt(email);
		logger.info("Sent General email with Attachment");
		return ResponseEntity.ok().build();
	}
	
}
