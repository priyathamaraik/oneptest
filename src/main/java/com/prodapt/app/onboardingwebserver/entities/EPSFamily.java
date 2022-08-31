package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("dev-eps-family")
public class EPSFamily implements Serializable {
	
	private static final long serialVersionUID = -5776928760649992708L;

	@Id
	private String id;
	
	@NotNull
	@NotBlank(message = "EPF Nominee Name cannot be blank!")
	private String name;
	
	@NotNull
	private long dateOfBirth;
	
	@NotNull
	@NotBlank(message = "Address cannot be blank!")
	private String address;
	
	@NotNull
	@NotBlank(message = "Relationship cannot be blank!")
	private String relationship;

	public EPSFamily() {
		this.id = UUID.randomUUID().toString();
	}

	public EPSFamily(@NotNull @NotBlank(message = "EPF Nominee Name cannot be blank!") String name,
			@NotNull long dateOfBirth, @NotNull @NotBlank(message = "Address cannot be blank!") String address,
			@NotNull @NotBlank(message = "Relationship cannot be blank!") String relationship) {
		super();
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.relationship = relationship;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public long getDateOfBirth() {
		return dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDateOfBirth(long dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
}
