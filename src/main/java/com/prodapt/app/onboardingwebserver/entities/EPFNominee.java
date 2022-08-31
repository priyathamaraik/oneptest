package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("dev-epf-nominee")
public class EPFNominee implements Serializable {

	private static final long serialVersionUID = -5981560602906110219L;

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
	
	@Max(value=100, message = "Max value cannot be over 100%")
	private int proportion;
	
	private String minorDetails;

	public EPFNominee() {
		this.id = UUID.randomUUID().toString();
	}

	public EPFNominee(String name,
			long dateOfBirth, 
			String address,
			String relationship,
			int proportion, 
			String minorDetails,
			long signDate,
			String nomineeSign ) {
		super();
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.relationship = relationship;
		this.proportion = proportion;
		this.minorDetails = minorDetails;
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

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public int getProportion() {
		return proportion;
	}

	public String getMinorDetails() {
		return minorDetails;
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

	public void setProportion(int proportion) {
		this.proportion = proportion;
	}

	public void setMinorDetails(String minorDetails) {
		this.minorDetails = minorDetails;
	}
	
}
