package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("dev-bank-details")
public class BankDetails implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8030233026547430652L;

	@Id
	private String id;
	
	@NotNull
	@NotBlank
	@Indexed
	private String empId;
	
	@NotNull
	@NotBlank
	@Indexed
	private String empName;
	
	
	@Indexed
	private String empCode;
	
	@Indexed
	private String prodaptEmail;
	
	@NotNull
	@Indexed
	private long dateOfJoining;
		
	@NotNull
	@NotBlank
	@Indexed
	private String bankName;
	
	@NotNull
	@NotBlank
	@Indexed
	private String branchName;
	
	@NotNull
	@NotBlank
	@Indexed
	private String accountNumber;
	
	@NotNull
	@NotBlank
	@Indexed
	private String ifscCode;
	
	@NotNull
	@NotBlank
	@Indexed
	private String docType;
	
	@NotNull
	@NotBlank
	@Indexed
	private String docImageUrl;

	@NotNull
	@NotBlank
	@Indexed
	private String urlType;
	
	
	@Indexed
	private boolean approved = false;
	
	@Indexed
	private String approvedBy;
	
	@Indexed
	private String remarks;
	
	
	private long createdOn;
	
	private long modifiedOn;

	public BankDetails() {
		this.id = UUID.randomUUID().toString();
		this.createdOn = Instant.now().toEpochMilli();
		this.modifiedOn = Instant.now().toEpochMilli();
	}

	public BankDetails(@NotNull @NotBlank String empId, @NotNull @NotBlank String empName,
			 
			@NotNull long dateOfJoining,
			@NotNull @NotBlank String bankName,
			@NotNull @NotBlank String branchName,
			@NotNull @NotBlank String accountNumber, @NotNull @NotBlank String ifscCode,
			@NotNull @NotBlank String docType, @NotNull @NotBlank String docImageUrl,
			@NotNull @NotBlank String urlType) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.dateOfJoining = dateOfJoining;
		this.bankName = bankName;
		this.branchName = branchName;
		this.accountNumber = accountNumber;
		this.ifscCode = ifscCode;
		this.docType = docType;
		this.docImageUrl = docImageUrl;
		this.urlType = urlType;
	}
	
	
	public String getProdaptEmail() {
		return prodaptEmail;
	}

	public void setProdaptEmail(String prodaptEmail) {
		this.prodaptEmail = prodaptEmail;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public long getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(long dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getId() {
		return id;
	}

	public String getEmpId() {
		return empId;
	}

	public String getEmpName() {
		return empName;
	}

	public String getEmpCode() {
		return empCode;
	}

	public String getBankName() {
		return bankName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public String getDocType() {
		return docType;
	}

	public String getDocImageUrl() {
		return docImageUrl;
	}

	public String getUrlType() {
		return urlType;
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

	public long getCreatedOn() {
		return createdOn;
	}

	public long getModifiedOn() {
		return modifiedOn;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public void setDocImageUrl(String docImageUrl) {
		this.docImageUrl = docImageUrl;
	}

	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setModifiedOn(long modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	
}
