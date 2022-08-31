package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("dev-travel")
public class Travel implements Serializable{

	private static final long serialVersionUID = -8697996837865304759L;
	
	@Id
	private String id;
	
	@NotNull
	@NotBlank(message = "Country Visisted cannot be blank!")
	private String countryVisited;
	
	@NotNull
	private long stayedFrom;
	
	@NotNull
	private long stayedTo;
	
	@NotNull
	@NotBlank(message = "Travel Type cannot be blank!")
	private String travelType;
	
	@NotNull
	@NotBlank(message = "Visa issued Embassy cannot be blank!")
	private String visaIssuedEmbassy;
	
	@NotNull
	@NotBlank(message = "Visa Type cannot be blank!")
	private String visaType;

	public Travel() {
		this.id = UUID.randomUUID().toString();
	}

	public Travel(String countryVisited,
			long stayedFrom,
			long stayedTo,
			String travelType,
			String visaIssuedEmbassy,
			String visaType) {
		super();
		this.countryVisited = countryVisited;
		this.stayedFrom = stayedFrom;
		this.stayedTo = stayedTo;
		this.travelType = travelType;
		this.visaIssuedEmbassy = visaIssuedEmbassy;
		this.visaType = visaType;
	}

	public String getId() {
		return id;
	}

	public String getCountryVisited() {
		return countryVisited;
	}

	public void setCountryVisited(String countryVisited) {
		this.countryVisited = countryVisited;
	}

	public long getStayedFrom() {
		return stayedFrom;
	}

	public void setStayedFrom(long stayedFrom) {
		this.stayedFrom = stayedFrom;
	}

	public long getStayedTo() {
		return stayedTo;
	}

	public void setStayedTo(long stayedTo) {
		this.stayedTo = stayedTo;
	}

	public String getTravelType() {
		return travelType;
	}

	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}

	public String getVisaIssuedEmbassy() {
		return visaIssuedEmbassy;
	}

	public void setVisaIssuedEmbassy(String visaIssuedEmbassy) {
		this.visaIssuedEmbassy = visaIssuedEmbassy;
	}

	public String getVisaType() {
		return visaType;
	}

	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}
	
}
