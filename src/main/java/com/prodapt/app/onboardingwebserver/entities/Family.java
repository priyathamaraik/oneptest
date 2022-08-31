package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("dev-family")
public class Family implements Serializable {

	private static final long serialVersionUID = 4722395492136590711L;
	
	@Id
	private String id;
	
	@NotNull
	@NotBlank(message = "Name cannot be blank!")
	private String name;
	
	@NotNull
	@NotBlank(message = "Relationship cannot be blank!")
	private String relation;
	
	@NotNull
	@NotBlank(message = "Maritial Status cannot be blank!")
	private String maritialStatus;
	
	@NotNull
	@NotBlank(message = "Occupation cannot be blank!")
	private String occupation;

	public Family() {
		this.id = UUID.randomUUID().toString();
	}

	public Family(String name,
			String relation,
			String maritialStatus,
			String occupation) {
		super();
		this.name = name;
		this.relation = relation;
		this.maritialStatus = maritialStatus;
		this.occupation = occupation;
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

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getMaritialStatus() {
		return maritialStatus;
	}

	public void setMaritialStatus(String maritialStatus) {
		this.maritialStatus = maritialStatus;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

}
