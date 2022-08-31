package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("dev-work")
public class Work implements Serializable {

	private static final long serialVersionUID = -2869686119275189236L;

	@Id
	private String id;

	@NotNull
	@NotBlank(message = "Work Place Company cannot be blank!")
	private String companyName;

	@NotNull
	@NotBlank(message = "Designation cannot be blank!")
	private String designation;

	@NotNull
	private long workedFrom;

	@NotNull
	private long workedTo;

	@NotNull
	@NotBlank(message = "Industry Type cannot be blank!")
	private String industryType;

	@NotNull
	@NotBlank(message = "Organization Type cannot be blank!")
	private String organizationType;

	private String workImage;
	private String fileType;

	private String workImage1;
	private String fileType1;

	private boolean approved;
	private String approvedBy;
	private long approvedOn;
	private String remarks;

	public Work() {
		this.id = UUID.randomUUID().toString();
	}

	public Work(String companyName, String designation, long workedFrom, long workedTo, String industryType,
			String organizationType, String workImage, String fileType) {
		super();
		this.companyName = companyName;
		this.designation = designation;
		this.workedFrom = workedFrom;
		this.workedTo = workedTo;
		this.industryType = industryType;
		this.organizationType = organizationType;
		this.workImage = workImage;
		this.fileType = fileType;
	}

	public String getWorkImage1() {
		return workImage1;
	}

	public void setWorkImage1(String workImage1) {
		this.workImage1 = workImage1;
	}

	public String getFileType1() {
		return fileType1;
	}

	public void setFileType1(String fileType1) {
		this.fileType1 = fileType1;
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

	public String getWorkImage() {
		return workImage;
	}

	public void setWorkImage(String workImage) {
		this.workImage = workImage;
	}

	public String getId() {
		return id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public long getWorkedFrom() {
		return workedFrom;
	}

	public void setWorkedFrom(long workedFrom) {
		this.workedFrom = workedFrom;
	}

	public long getWorkedTo() {
		return workedTo;
	}

	public void setWorkedTo(long workedTo) {
		this.workedTo = workedTo;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public String getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(String organizationType) {
		this.organizationType = organizationType;
	}

}
