package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("dev-identity")
public class Identity implements Serializable {
	
	private static final long serialVersionUID = -4903055974802573493L;

	@Id
	private String id;
	
	@NotNull
	@NotBlank(message = "Document Name cannot be blank!")
	private String docName;
	
	@NotNull
	@NotBlank(message = "Document Ref Number cannot be blank!")
	private String docNumber;
	
	@NotNull
	@NotBlank(message = "Name as in Document cannot be blank!")
	private String nameInDoc;
	
	@NotNull
	@NotBlank(message = "Document Issued Country cannot be blank!")
	private String docIssuedCountry;
	
	private long docValidFrom;
	private long docValidTo;
	
	private String identityImage;
	private String fileType;
	
	private boolean approved;
	private String approvedBy;
	private long approvedOn;
	private String remarks;
	
	public Identity() {
		this.id = UUID.randomUUID().toString();
	}

	public Identity(String docName,
			String docNumber,
			String nameInDoc,
			String docIssuedCountry,
			long docValidFrom, 
			long docValidTo,
			String identityImage,
			String fileType) {
		super();
		this.docName = docName;
		this.docNumber = docNumber;
		this.nameInDoc = nameInDoc;
		this.docIssuedCountry = docIssuedCountry;
		this.docValidFrom = docValidFrom;
		this.docValidTo = docValidTo;
		this.identityImage = identityImage;
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

	public String getIdentityImage() {
		return identityImage;
	}

	public void setIdentityImage(String identityImage) {
		this.identityImage = identityImage;
	}

	public String getId() {
		return id;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}

	public String getNameInDoc() {
		return nameInDoc;
	}

	public void setNameInDoc(String nameInDoc) {
		this.nameInDoc = nameInDoc;
	}

	public String getDocIssuedCountry() {
		return docIssuedCountry;
	}

	public void setDocIssuedCountry(String docIssuedCountry) {
		this.docIssuedCountry = docIssuedCountry;
	}

	public long getDocValidFrom() {
		return docValidFrom;
	}

	public void setDocValidFrom(long docValidFrom) {
		this.docValidFrom = docValidFrom;
	}

	public long getDocValidTo() {
		return docValidTo;
	}

	public void setDocValidTo(long docValidTo) {
		this.docValidTo = docValidTo;
	}
	
}
