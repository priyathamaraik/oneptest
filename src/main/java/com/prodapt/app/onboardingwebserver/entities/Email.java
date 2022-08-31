package com.prodapt.app.onboardingwebserver.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Email {
	
	@javax.validation.constraints.Email
	@NotNull
	@NotBlank
	private String toEmail;
	
	@NotNull
	@NotBlank
	private String subject;
	
	
	private String intro;
	
	@NotNull
	@NotBlank
	private String body;
	
	private String attachmentUrl;
	private String attachmentName;
	private String attachementType;

	public Email() {
		
	}

	public Email(@javax.validation.constraints.Email @NotNull @NotBlank String toEmail,
			@NotNull @NotBlank String subject, @NotNull @NotBlank String body, String intro) {
		super();
		this.toEmail = toEmail;
		this.subject = subject;
		this.body = body;
		this.intro = intro;
	}
	

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getAttachementType() {
		return attachementType;
	}

	public void setAttachementType(String attachementType) {
		this.attachementType = attachementType;
	}

	public String getAttachmentUrl() {
		return attachmentUrl;
	}

	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}

	public String getToEmail() {
		return toEmail;
	}

	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	
	

}
