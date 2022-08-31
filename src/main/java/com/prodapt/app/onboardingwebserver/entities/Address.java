package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("dev-address")
public class Address implements Serializable {

	private static final long serialVersionUID = 5964391725120103566L;
	
	@Id
	private String id;
	
	@NotNull
	@NotBlank(message = "Address type cannot be blank!")
	private String type;
	
	@NotNull
	@NotBlank(message = "Country cannot be blank!")
	private String country;
	
	@NotNull
	@NotBlank(message = "State cannot be blank!")
	private String state;
	
	@NotNull
	@NotBlank(message = "City cannot be blank!")
	private String city;
	
	@NotNull
	@NotBlank(message = "Address Line 1 cannot be blank!")
	private String addressLine1;
	private String addressLine2;
	
	@NotNull
	@NotBlank(message = "Zip/Postal Code cannot be blank!")
	private String zipOrPostalCode;
	
	private String addressImage;
	private boolean isApproved;
	private String approvedBy;
	private long approvedOn;
	private String remarks;

	public Address() {
		this.id = UUID.randomUUID().toString();
	}

	public Address(String type,
			String country,
			String state,
			String city,
			String addressLine1, 
			String addressLine2,
			String zipOrPostalCode,
			String addressImage) {
		super();
		this.type = type;
		this.country = country;
		this.state = state;
		this.city = city;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.zipOrPostalCode = zipOrPostalCode;
		this.addressImage = addressImage;
	}
	
	
	public boolean isApproved() {
		return isApproved;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public long getApprovedOn() {
		return approvedOn;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public void setApprovedOn(long approvedOn) {
		this.approvedOn = approvedOn;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAddressImage() {
		return addressImage;
	}

	public void setAddressImage(String addressImage) {
		this.addressImage = addressImage;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getZipOrPostalCode() {
		return zipOrPostalCode;
	}

	public void setZipOrPostalCode(String zipOrPostalCode) {
		this.zipOrPostalCode = zipOrPostalCode;
	}

	
}
