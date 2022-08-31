package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("dev-contact-spoc")
public class ContactSpoc implements Serializable {

	private static final long serialVersionUID = 5964391725120103566L;

	@Id
	private String id;

	@NotNull
	@NotBlank(message = "Name type cannot be blank!")
	private String name;

	@NotNull
	@NotBlank(message = "Email-id cannot be blank!")
	private String email;

	@NotNull
	@NotBlank(message = "Mobile Number cannot be blank!")
	private String mobileNumber;

	public ContactSpoc() {
		this.id = UUID.randomUUID().toString();
	}


	public ContactSpoc(String name, String email, String mobileNumber, String designation) {
		super();
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
}
