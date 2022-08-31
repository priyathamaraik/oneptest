package com.prodapt.app.onboardingwebserver.entities;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

@RedisHash("dev-relation")
public class Relation implements Serializable {
	
	private static final long serialVersionUID = -6925327860294817281L;

	@Id
	private String id;
	
	@NotBlank(message = "Relation Name cannot be blank!")
	@NotNull
	@Indexed
	private String name;
	
	@NotBlank(message = "Relation Type cannot be blank!")
	@NotNull
	@Indexed
	private String type;
	
	@NotBlank(message = "Relation Gender cannot be blank!")
	@NotNull
	@Indexed
	private String gender;
	
	@NotNull
	@Indexed
	private long dateOfBirth;
	
	private String isDependant;
	
	public Relation() {
		this.id = UUID.randomUUID().toString();
	}

	public Relation(String name,
			String type,
			String gender,
			long dateOfBirth,
			String isDependant
			) {
		super();
		this.name = name;
		this.type = type;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.isDependant = isDependant;
		
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(long dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getIsDependant() {
		return isDependant;
	}

	public void setIsDependant(String isDependant) {
		this.isDependant = isDependant;
	}
	
}
