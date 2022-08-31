package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("dev-candidate")
public class Candidate implements Serializable {

	private static final long serialVersionUID = -1677867494385001596L;

	@Id
	private String id;

	@Email(message = "Valid email is required!")
	@NotBlank
	@NotNull
	@Indexed
	private String email;

	@NotNull
	@NotBlank
	@Indexed
	private String name;

	@Indexed
	private String empCode;

	@Indexed
	private String prodaptEmail;

	private String photoImage;

	@NotNull
	@Indexed
	private long dateOfJoining;

	@NotNull
	@NotBlank
	@Indexed
	private String highestQualification;

	@NotNull
	@NotBlank
	@Indexed
	private String modeOfRecruitment;

	@Indexed
	private String status = "Pending";

	@NotNull
	@NotBlank
	@Indexed
	private String country;

	@NotNull
	@NotBlank
	@Indexed
	private String region;

	@Indexed
	private int percentProgress = 0;

	@Indexed
	private boolean declarationsDone = false;

	@Indexed
	private boolean candidateDetailsDone = false;

	@Indexed
	private boolean idCardDone = false;

	@Indexed
	private boolean mediclaimDone = false;

	@Indexed
	private boolean gratuityDone = false;

	@Indexed
	private boolean epfkycDone = false;

	@Indexed
	private boolean epfNominationDone = false;

	@Indexed
	private boolean bankDetailsDone = false;
	
	@Indexed
	private boolean welfareDetailsDone = false;

	@Indexed
	private boolean consentFormAccepted = false;

	@Indexed
	private ContactSpoc hrbpSpoc;

	@Indexed
	private ContactSpoc proAdvisorSpoc;
	
	@Indexed
	private ContactSpoc buddySpoc;

	public ContactSpoc getBuddySpoc() {
		return buddySpoc;
	}

	public void setBuddySpoc(ContactSpoc buddySpoc) {
		this.buddySpoc = buddySpoc;
	}

	@Indexed
	private String category;

	@Indexed
	private String practiceTag;

	@Indexed
	private String department;

	@Indexed
	private String reportingManagerName;

	@Indexed
	private String reportingManageremail;

	private String password = "123456";

	@Indexed
	private String role = "USER";

	@Indexed
	private boolean enabled = true;
	private long created;
	private long modified;

	@Indexed
	private String createdBy;

	@NotNull
	@NotBlank
	@Indexed
	private String modifiedBy;

	public Candidate() {
		this.id = UUID.randomUUID().toString();
		this.created = Instant.now().toEpochMilli();
		this.modified = Instant.now().toEpochMilli();
	}

	public Candidate(String email, String name, long dateOfJoining, String highestQualification,
			String modeOfRecruitment, String status, String country, String region, ContactSpoc hrbpSpoc,
			ContactSpoc proAdvisorSpoc,ContactSpoc buddySpoc, String category, String practiceTag, String reportingManagerName,
			String department, String reportingManageremail, String createdBy, String modifiedBy) {
		super();
		this.email = email;
		this.name = name;

		this.dateOfJoining = dateOfJoining;
		this.highestQualification = highestQualification;
		this.modeOfRecruitment = modeOfRecruitment;
		this.status = status;
		this.country = country;
		this.region = region;
		this.hrbpSpoc = hrbpSpoc;
		this.proAdvisorSpoc = proAdvisorSpoc;
		this.buddySpoc = buddySpoc;
		this.category = category;
		this.practiceTag = practiceTag;
		this.reportingManagerName = reportingManagerName;
		this.department = department;
		this.reportingManageremail = reportingManageremail;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setPracticeTag(String practiceTag) {
		this.practiceTag = practiceTag;
	}

	public String getPracticeTag() {
		return practiceTag;
	}

	public ContactSpoc getHrbpSpoc() {
		return hrbpSpoc;
	}

	public void setHrbpSpoc(ContactSpoc hrbpSpoc) {
		this.hrbpSpoc = hrbpSpoc;
	}

	public ContactSpoc getProAdvisorSpoc() {
		return proAdvisorSpoc;
	}

	public void setProAdvisorSpoc(ContactSpoc proAdvisorSpoc) {
		this.proAdvisorSpoc = proAdvisorSpoc;
	}

	public boolean isDeclarationsDone() {
		return declarationsDone;
	}

	public void setDeclarationsDone(boolean declarationsDone) {
		this.declarationsDone = declarationsDone;
	}

	public String getReportingManagerName() {
		return reportingManagerName;
	}

	public void setReportingManagerName(String reportingManagerName) {
		this.reportingManagerName = reportingManagerName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getReportingManageremail() {
		return reportingManageremail;
	}

	public void setReportingManageremail(String reportingManageremail) {
		this.reportingManageremail = reportingManageremail;
	}

	public boolean isConsentFormAccepted() {
		return consentFormAccepted;
	}

	public void setConsentFormAccepted(boolean consentFormAccepted) {
		this.consentFormAccepted = consentFormAccepted;
	}

	public boolean isCandidateDetailsDone() {
		return candidateDetailsDone;
	}

	public boolean isIdCardDone() {
		return idCardDone;
	}

	public boolean isMediclaimDone() {
		return mediclaimDone;
	}

	public boolean isGratuityDone() {
		return gratuityDone;
	}

	public boolean isEpfkycDone() {
		return epfkycDone;
	}

	public boolean isEpfNominationDone() {
		return epfNominationDone;
	}

	public boolean isBankDetailsDone() {
		return bankDetailsDone;
	}

	public void setWelfareDetailsDone(boolean welfareDetailsDone) {
		this.welfareDetailsDone = welfareDetailsDone;
	}

	public void setCandidateDetailsDone(boolean candidateDetailsDone) {
		this.candidateDetailsDone = candidateDetailsDone;
	}

	public void setIdCardDone(boolean idCardDone) {
		this.idCardDone = idCardDone;
	}

	public void setMediclaimDone(boolean mediclaimDone) {
		this.mediclaimDone = mediclaimDone;
	}

	public void setGratuityDone(boolean gratuityDone) {
		this.gratuityDone = gratuityDone;
	}

	public void setEpfkycDone(boolean epfkycDone) {
		this.epfkycDone = epfkycDone;
	}

	public void setEpfNominationDone(boolean epfNominationDone) {
		this.epfNominationDone = epfNominationDone;
	}

	public void setBankDetailsDone(boolean bankDetailsDone) {
		this.bankDetailsDone = bankDetailsDone;
	}
	
	public boolean isWelfareDetailsDone() {
		return welfareDetailsDone;
	}

	public int getPercentProgress() {
		return percentProgress;
	}

	public void setPercentProgress(int percentProgress) {
		this.percentProgress = percentProgress;
	}

	public String getEmpCode() {
		return empCode;
	}

	public String getProdaptEmail() {
		return prodaptEmail;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public void setProdaptEmail(String prodaptEmail) {
		this.prodaptEmail = prodaptEmail;
	}

	public String getPhotoImage() {
		return photoImage;
	}

	public void setPhotoImage(String photoImage) {
		this.photoImage = photoImage;
	}

	public long getDateOfJoining() {
		return dateOfJoining;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setDateOfJoining(long dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public void setModified(long modified) {
		this.modified = modified;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getHighestQualification() {
		return highestQualification;
	}

	public String getModeOfRecruitment() {
		return modeOfRecruitment;
	}

	public String getStatus() {
		return status;
	}

	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
	}

	public void setModeOfRecruitment(String modeOfRecruitment) {
		this.modeOfRecruitment = modeOfRecruitment;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setCreated(Long created) {
		this.created = created;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Long getCreated() {
		return created;
	}

	public Long getModified() {
		return modified;
	}

	public void setModified(Long modified) {
		this.modified = modified;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bankDetailsDone, candidateDetailsDone, consentFormAccepted, country, created, createdBy,
				dateOfJoining, declarationsDone, department, email, empCode, enabled, epfNominationDone, epfkycDone,
				gratuityDone, highestQualification, hrbpSpoc, id, idCardDone, mediclaimDone, modeOfRecruitment,
				modified, modifiedBy, name, password, percentProgress, photoImage, proAdvisorSpoc, prodaptEmail, region,
				reportingManagerName, reportingManageremail, role, status,buddySpoc);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candidate other = (Candidate) obj;
		return bankDetailsDone == other.bankDetailsDone && candidateDetailsDone == other.candidateDetailsDone
				&& consentFormAccepted == other.consentFormAccepted && Objects.equals(country, other.country)
				&& created == other.created && Objects.equals(createdBy, other.createdBy)
				&& dateOfJoining == other.dateOfJoining && declarationsDone == other.declarationsDone
				&& Objects.equals(department, other.department) && Objects.equals(email, other.email)
				&& Objects.equals(empCode, other.empCode) && enabled == other.enabled
				&& epfNominationDone == other.epfNominationDone && epfkycDone == other.epfkycDone
				&& gratuityDone == other.gratuityDone
				&& Objects.equals(highestQualification, other.highestQualification)
				&& Objects.equals(hrbpSpoc, other.hrbpSpoc) && Objects.equals(id, other.id)
				&& idCardDone == other.idCardDone && mediclaimDone == other.mediclaimDone
				&& Objects.equals(modeOfRecruitment, other.modeOfRecruitment) && modified == other.modified
				&& Objects.equals(modifiedBy, other.modifiedBy) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && percentProgress == other.percentProgress
				&& Objects.equals(photoImage, other.photoImage) && Objects.equals(proAdvisorSpoc, other.proAdvisorSpoc)
				&& Objects.equals(buddySpoc, other.buddySpoc)
				&& Objects.equals(prodaptEmail, other.prodaptEmail) && Objects.equals(region, other.region)
				&& Objects.equals(reportingManagerName, other.reportingManagerName)
				&& Objects.equals(reportingManageremail, other.reportingManageremail)
				&& Objects.equals(role, other.role) && Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		return "Candidate [id=" + id + ", email=" + email + ", name=" + name + ", empCode=" + empCode
				+ ", prodaptEmail=" + prodaptEmail + ", photoImage=" + photoImage + ", dateOfJoining=" + dateOfJoining
				+ ", highestQualification=" + highestQualification + ", modeOfRecruitment=" + modeOfRecruitment
				+ ", status=" + status + ", country=" + country + ", region=" + region + ", percentProgress="
				+ percentProgress + ", declarationsDone=" + declarationsDone + ", candidateDetailsDone="
				+ candidateDetailsDone + ", idCardDone=" + idCardDone + ", mediclaimDone=" + mediclaimDone
				+ ", gratuityDone=" + gratuityDone + ", epfkycDone=" + epfkycDone + ", epfNominationDone="
				+ epfNominationDone + ", bankDetailsDone=" + bankDetailsDone + ", welfareDetailsDone=" + welfareDetailsDone 
				+ ", consentFormAccepted=" + consentFormAccepted + ", hrbpSpoc=" + hrbpSpoc + ", proAdvisorSpoc=" + proAdvisorSpoc
				+", buddySpoc=" + buddySpoc + ", reportingManagerName=" + reportingManagerName + ", department=" + department
				+ ", reportingManageremail=" + reportingManageremail + ", password=" + password + ", role=" + role
				+ ", enabled=" + enabled + ", created=" + created + ", modified=" + modified + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy + "]";
	}


}
