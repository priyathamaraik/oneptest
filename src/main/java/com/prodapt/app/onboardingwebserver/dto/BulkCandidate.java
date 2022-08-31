package com.prodapt.app.onboardingwebserver.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BulkCandidate {
	@JsonProperty
	private String candidateName;
	@JsonProperty
    private String candidateEmail;
    public BulkCandidate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BulkCandidate(String candidateName, String candidateEmail, Date dateOfJoining, String highestQualification,
			String modeOfEmployment, String department, String category, String region, String country, String hRBPName,
			String preBAPAdvisorName, String reportingManagerName, String reportingManagerEmail, String practiceTag) {
		super();
		this.candidateName = candidateName;
		this.candidateEmail = candidateEmail;
		this.dateOfJoining = dateOfJoining;
		this.highestQualification = highestQualification;
		this.modeOfEmployment = modeOfEmployment;
		this.department = department;
		this.category = category;
		this.region = region;
		this.country = country;
		this.hRBPName = hRBPName;
		this.preBAPAdvisorName = preBAPAdvisorName;
		this.reportingManagerName = reportingManagerName;
		this.reportingManagerEmail = reportingManagerEmail;
		this.practiceTag = practiceTag;
	}
	@JsonProperty
	private Date dateOfJoining;
	@JsonProperty
    private String highestQualification;
	@JsonProperty
    private String modeOfEmployment;
	@JsonProperty
    private String department;
	@JsonProperty
    private String category;
	@JsonProperty
    private String region;
	@JsonProperty
    private String country;
	@JsonProperty
    private String hRBPName;
	@JsonProperty
    private String preBAPAdvisorName;
	@JsonProperty
    private String reportingManagerName;
	@JsonProperty
    private String reportingManagerEmail;
	@JsonProperty
    private String practiceTag;
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getCandidateEmail() {
		return candidateEmail;
	}
	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getHighestQualification() {
		return highestQualification;
	}
	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
	}
	public String getModeOfEmployment() {
		return modeOfEmployment;
	}
	public void setModeOfEmployment(String modeOfEmployment) {
		this.modeOfEmployment = modeOfEmployment;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String gethRBPName() {
		return hRBPName;
	}
	public void sethRBPName(String hRBPName) {
		this.hRBPName = hRBPName;
	}
	public String getPreBAPAdvisorName() {
		return preBAPAdvisorName;
	}
	public void setPreBAPAdvisorName(String preBAPAdvisorName) {
		this.preBAPAdvisorName = preBAPAdvisorName;
	}
	public String getReportingManagerName() {
		return reportingManagerName;
	}
	public void setReportingManagerName(String reportingManagerName) {
		this.reportingManagerName = reportingManagerName;
	}
	public String getReportingManagerEmail() {
		return reportingManagerEmail;
	}
	public void setReportingManagerEmail(String reportingManagerEmail) {
		this.reportingManagerEmail = reportingManagerEmail;
	}
	public String getPracticeTag() {
		return practiceTag;
	}
	public void setPracticeTag(String practiceTag) {
		this.practiceTag = practiceTag;
	}

}
