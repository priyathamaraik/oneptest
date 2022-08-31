package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("dev-education")
public class Education implements Serializable {

	private static final long serialVersionUID = 1689265458631991653L;

	@Id
	private String id;

	@NotNull
	@NotBlank(message = "Education Name cannot be blank!")
	private String educationName;

	@NotNull
	@NotBlank(message = "Institution Name cannot blank!")
	private String institution;

	@NotNull
	@NotBlank(message = "Board/University Name cannot blank!")
	private String boardOrUniversity;

	@NotNull
	@NotBlank(message = "Specialization cannot blank!")
	private String specialization;

	private String classOrCGPA;

	@NotNull
	private long studiedFrom;

	@NotNull
	private long studiedTo;

	private String educationImage;
	private String fileType;

	private String educationImage1;
	private String fileType1;

	private String educationImage2;
	private String fileType2;

	private boolean approved;
	private String approvedBy;
	private long approvedOn;
	private String remarks;

	public Education() {
		this.id = UUID.randomUUID().toString();
	}

	public Education(String educationName, String institution, String boardOrUniversity, String specialization,
			long studiedFrom, long studiedTo, String educationImage, String fileType, String classOrCGPA) {
		super();
		this.educationName = educationName;
		this.institution = institution;
		this.boardOrUniversity = boardOrUniversity;
		this.studiedFrom = studiedFrom;
		this.studiedTo = studiedTo;
		this.specialization = specialization;
		this.educationImage = educationImage;
		this.fileType = fileType;
		this.classOrCGPA = classOrCGPA;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getClassOrCGPA() {
		return classOrCGPA;
	}

	public void setClassOrCGPA(String classOrCGPA) {
		this.classOrCGPA = classOrCGPA;
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

	public String getEducationImage() {
		return educationImage;
	}

	public void setStudiedFrom(long studiedFrom) {
		this.studiedFrom = studiedFrom;
	}

	public void setStudiedTo(long studiedTo) {
		this.studiedTo = studiedTo;
	}

	public void setEducationImage(String educationImage) {
		this.educationImage = educationImage;
	}

	public String getId() {
		return id;
	}

	public String getEducationName() {
		return educationName;
	}

	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getBoardOrUniversity() {
		return boardOrUniversity;
	}

	public void setBoardOrUniversity(String boardOrUniversity) {
		this.boardOrUniversity = boardOrUniversity;
	}

	public Long getStudiedFrom() {
		return studiedFrom;
	}

	public void setStudiedFrom(Long studiedFrom) {
		this.studiedFrom = studiedFrom;
	}

	public Long getStudiedTo() {
		return studiedTo;
	}

	public void setStudiedTo(Long studiedTo) {
		this.studiedTo = studiedTo;
	}

	public String getEducationImage1() {
		return educationImage1;
	}

	public void setEducationImage1(String educationImage1) {
		this.educationImage1 = educationImage1;
	}

	public String getFileType1() {
		return fileType1;
	}

	public void setFileType1(String fileType1) {
		this.fileType1 = fileType1;
	}

	public String getEducationImage2() {
		return educationImage2;
	}

	public void setEducationImage2(String educationImage2) {
		this.educationImage2 = educationImage2;
	}

	public String getFileType2() {
		return fileType2;
	}

	public void setFileType2(String fileType2) {
		this.fileType2 = fileType2;
	}

}
