package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("dev-gratuity-nominee")
public class GratuityNominee implements Serializable {
	
	private static final long serialVersionUID = -3853239072881610139L;

	@Id
	private String id;
	
	@NotNull
	@NotBlank(message = "Nominee Name cannot be blank!")
	private String name;
	
	@NotNull
	@NotBlank(message = "Nominee Relationship cannot be blank!")
	private String relationship;
	
	@Max(value=100, message = "Max value cannot be over 100%")
	@NotNull
	private int proportion;
	
	@NotNull
	@NotBlank(message = "Nominee Age cannot be blank!")
	private String age;
	
	@NotNull
	@NotBlank(message = "Nominee Address cannot be blank!")
	private String address;
	
	public GratuityNominee() {
		this.id = UUID.randomUUID().toString();
	}

	public GratuityNominee(String name, String relationship, int proportion, String age, String address) {
		super();
		this.name = name;
		this.relationship = relationship;
		this.proportion = proportion;
		this.age = age;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setProportion(int proportion) {
		this.proportion = proportion;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
