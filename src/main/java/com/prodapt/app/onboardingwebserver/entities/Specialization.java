package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("dev-specialization")
public class Specialization implements Serializable {

	private static final long serialVersionUID = -1592613211654884251L;

	@Id
	private String id;

	@NotNull
	@NotBlank(message = "Specialization Name cannot be blank!")
	private String name;
	
	@NotNull
	@NotBlank(message = "Specialization Details cannot be blank!")
	private String details;
	
	@NotNull
	private long dateOfCompletion;
	
	private String specializationImage;
	private String fileType;
	
	private boolean approved;
	private String approvedBy;
	private long approvedOn;
	private String remarks;

	public Specialization() {
		this.id = UUID.randomUUID().toString();
	}

	public Specialization(String name,
			String details,
			long dateOfCompletion,
			String specializationImage,
			String fileType) {
		super();
		this.name = name;
		this.details = details;
		this.dateOfCompletion = dateOfCompletion;
		this.specializationImage = specializationImage;
		this.fileType = fileType;
	}

	
	
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public boolean isApproved() {
		return approved;
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
		this.approved = isApproved;
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

	public String getSpecializationImage() {
		return specializationImage;
	}

	public void setSpecializationImage(String specializationImage) {
		this.specializationImage = specializationImage;
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

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public long getDateOfCompletion() {
		return dateOfCompletion;
	}

	public void setDateOfCompletion(long dateOfCompletion) {
		this.dateOfCompletion = dateOfCompletion;
	}
	
	
}
