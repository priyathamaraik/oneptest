package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("dev-vehicle")
public class Vehicle implements Serializable {

	private static final long serialVersionUID = 4177965148504977996L;

	@Id
	private String id;
	
	@NotBlank(message = "Vehicle Type cannot be blank!")
	@NotNull
	@Indexed
	private String type;
	
	@NotBlank(message = "Vehicle Name cannot be blank!")
	@NotNull
	@Indexed
	private String make;
	
	@NotBlank(message = "Vehicle Model cannot be blank!")
	@NotNull
	@Indexed
	private String model;
	
	@NotBlank(message = "Registration Number cannot be blank!")
	@NotNull
	@Indexed
	private String regnNo;

	public Vehicle() {
		this.id = UUID.randomUUID().toString();
	}

	public Vehicle(String type, String make, String model, String regnNo) {
		super();
		this.type = type;
		this.make = make;
		this.model = model;
		this.regnNo = regnNo;
	}

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getRegnNo() {
		return regnNo;
	}

	public void setRegnNo(String regnNo) {
		this.regnNo = regnNo;
	}

}
