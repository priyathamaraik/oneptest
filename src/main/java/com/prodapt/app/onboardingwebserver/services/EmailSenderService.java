package com.prodapt.app.onboardingwebserver.services;

import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.prodapt.app.onboardingwebserver.entities.Candidate;
import com.prodapt.app.onboardingwebserver.entities.Email;
import com.prodapt.app.onboardingwebserver.entities.HrUser;
import com.prodapt.app.onboardingwebserver.repository.CandidateRepository;
import com.prodapt.app.onboardingwebserver.repository.HrUserRepository;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private SpringTemplateEngine springTemplateEngine;
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private HrUserRepository hrUserRepository;

	public void sendWelcomeEmail(@Valid Email email) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

		mimeMessageHelper.setFrom("propad.notifications@prodapt.com");
		mimeMessageHelper.setTo(email.getToEmail());
		mimeMessageHelper.setSubject(email.getSubject());

		Context context = new Context();
		context.setVariable("body", email.getBody());

		String htmlEmail = springTemplateEngine.process("welcome_email", context);
		mimeMessageHelper.setText(htmlEmail, true);

		javaMailSender.send(mimeMessage);
		System.out.println("Mail Sent...");
	}
	
	public void sendGeneralEmail(@Valid Email email) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

		mimeMessageHelper.setFrom("propad.notifications@prodapt.com");
		mimeMessageHelper.setTo(email.getToEmail());
		mimeMessageHelper.setSubject(email.getSubject());
		
		Optional<Candidate> candidate = candidateRepository.findByEmail(email.getToEmail());
		if(candidate.isPresent()) {
			Context context = new Context();
			context.setVariable("body", email.getBody());
			context.setVariable("intro", email.getIntro());
			context.setVariable("name", candidate.get().getName());

			String htmlEmail = springTemplateEngine.process("general_email", context);
			mimeMessageHelper.setText(htmlEmail, true);

			javaMailSender.send(mimeMessage);
			System.out.println("Mail Sent...");
		}
	}
	
	public void sendGeneralEmailHr(@Valid Email email) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

		mimeMessageHelper.setFrom("propad.notifications@prodapt.com");
		mimeMessageHelper.setTo(email.getToEmail());
		mimeMessageHelper.setSubject(email.getSubject());
		
		Optional<HrUser> hrUser = hrUserRepository.findByEmail(email.getToEmail());
		if(hrUser.isPresent()) {
			Context context = new Context();
			context.setVariable("body", email.getBody());
			context.setVariable("intro", email.getIntro());
			context.setVariable("name", hrUser.get().getName());

			String htmlEmail = springTemplateEngine.process("general_email", context);
			mimeMessageHelper.setText(htmlEmail, true);

			javaMailSender.send(mimeMessage);
			System.out.println("Mail Sent...");
		}
	}
	
	public void sendSimpleEmail(@Valid Email email) throws MessagingException {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

		simpleMailMessage.setFrom("propad.notifications@prodapt.com");
		simpleMailMessage.setTo(email.getToEmail());
		simpleMailMessage.setText(email.getBody());
		simpleMailMessage.setSubject(email.getSubject());

		javaMailSender.send(simpleMailMessage);
		System.out.println("Mail Sent...");
	}

	public void sendEmailWithAttachment(@Valid Email email) throws MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

		mimeMessageHelper.setFrom("propad.notifications@prodapt.com");
		mimeMessageHelper.setTo(email.getToEmail());
		mimeMessageHelper.setSubject(email.getSubject());
		
		mimeMessageHelper.setText(email.getBody());

		String fileUrl = email.getAttachmentUrl();
		if (email.getAttachementType().equals("pdf")) {
			fileUrl = fileUrl.replaceFirst("^data:application/[^;]*;base64,?", "");
		} else {
			fileUrl = fileUrl.replaceFirst("^data:image/[^;]*;base64,?", "");
		}

		byte[] bytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(fileUrl);

		mimeMessageHelper.addAttachment(email.getAttachmentName() + "." + email.getAttachementType(),
				new ByteArrayResource(bytes));

		javaMailSender.send(mimeMessage);
		System.out.println("Mail Sent...");
	}
	
	public void sendEmailTemplateWithAttachemnt(@Valid Email email) throws MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

		mimeMessageHelper.setFrom("propad.notifications@prodapt.com");
		mimeMessageHelper.setTo(email.getToEmail());
		mimeMessageHelper.setSubject(email.getSubject());
		
		Optional<Candidate> candidate = candidateRepository.findByEmail(email.getToEmail());
		if(candidate.isPresent()) {
		Context context = new Context();
		context.setVariable("body", email.getBody());
		context.setVariable("intro", email.getIntro());
		context.setVariable("name", candidate.get().getName());

		String fileUrl = email.getAttachmentUrl();
		if (email.getAttachementType().equals("pdf")) {
			fileUrl = fileUrl.replaceFirst("^data:application/[^;]*;base64,?", "");
		} else {
			fileUrl = fileUrl.replaceFirst("^data:image/[^;]*;base64,?", "");
		}

		byte[] bytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(fileUrl);

		mimeMessageHelper.addAttachment(email.getAttachmentName() + "." + email.getAttachementType(),
				new ByteArrayResource(bytes));
		
		String htmlEmail = springTemplateEngine.process("general_email", context);
		mimeMessageHelper.setText(htmlEmail, true);

		javaMailSender.send(mimeMessage);
		System.out.println("Mail Sent...");
		}
	}

}
