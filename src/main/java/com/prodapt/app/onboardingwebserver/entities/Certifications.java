package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("dev-certifications")
public class Certifications implements Serializable {

	private static final long serialVersionUID = 4698389477696833126L;
	
	@Id
	private String id;
	
	@NotNull
	@NotBlank(message = "Certification Name cannot be blank!")
	private String name;
	
	@NotNull
	@NotBlank(message = "Certification Issued By cannot be blank!")
	private String issuedBy;
	
	@NotNull
	private long issuedOn;
	
	private String certificationImage;
	private String fileType;
	
	private boolean approved;
	private String approvedBy;
	private long approvedOn;
	private String remarks;
	
	private long validFrom;
	private long validTo;
	
	public Certifications() {
		this.id = UUID.randomUUID().toString();
	}

	public Certifications(String name,
			String issuedBy,
			long issuedOn, 
			long validFrom,
			long validTo,
			String certificationImage,
			String fileType) {
		super();
		this.name = name;
		this.issuedBy = issuedBy;
		this.issuedOn = issuedOn;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.certificationImage = certificationImage;
		this.fileType = fileType;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public long getApprovedOn() {
		return approvedOn;
	}

	public void setApprovedOn(long approvedOn) {
		this.approvedOn = approvedOn;
	}

	public boolean isApproved() {
		return approved;
	}

	public String getApprovedBy() {
		return approvedBy;
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

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCertificationImage() {
		return certificationImage;
	}

	public void setCertificationImage(String certificationImage) {
		this.certificationImage = certificationImage;
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

	public String getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}

	public long getIssuedOn() {
		return issuedOn;
	}

	public void setIssuedOn(long issuedOn) {
		this.issuedOn = issuedOn;
	}

	public long getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(long validFrom) {
		this.validFrom = validFrom;
	}

	public long getValidTo() {
		return validTo;
	}

	public void setValidTo(long validTo) {
		this.validTo = validTo;
	}

}
