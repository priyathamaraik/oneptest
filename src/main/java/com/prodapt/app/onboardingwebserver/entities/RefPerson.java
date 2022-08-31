package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("dev-refperson")
public class RefPerson implements Serializable {

	private static final long serialVersionUID = -8848357390444104947L;
	
	@Id
	private String id;
	
	@NotNull
	@NotBlank(message = "Reference Person Name cannot be blank!")
	private String name;
	
	@NotNull
	@NotBlank(message = "Designation cannot be blank!")
	private String designation;
	
	@NotNull
	@NotBlank(message = "Mobile Number cannot be blank")
	private String mobileNumber;
	
	private String address;

	public RefPerson() {
		this.id = UUID.randomUUID().toString();
	}

	public RefPerson(String name,
			String designation,
			String mobileNumber, 
			String address) {
		super();
		this.name = name;
		this.designation = designation;
		this.mobileNumber = mobileNumber;
		this.address = address;
	}

	public String getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

}
