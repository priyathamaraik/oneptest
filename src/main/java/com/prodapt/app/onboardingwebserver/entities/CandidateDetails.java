package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("dev-candidate-details")
public class CandidateDetails implements Serializable {

	private static final long serialVersionUID = 1360832608100530952L;

	@Id
	private String id;

	@NotNull
	@NotBlank(message = "Emp Id cannot be blank!")
	@Indexed
	private String empId;

	@Indexed
	private String empCode;

	@Indexed
	private String prodaptEmail;

	@NotNull
	@NotBlank(message = "First Name cannot be blank!")
	@Indexed
	private String firstName;

	@NotNull
	@NotBlank(message = "Last Name cannot be blank!")
	@Indexed
	private String lastName;

	@NotNull
	@Indexed
	private long dateOfBirth;

	@NotNull
	@Indexed
	private long dateOfJoining;
	
	private String uan;

	@NotNull
	@NotBlank(message = "Maritial Status cannot be blank!")
	@Indexed
	private String maritalStatus;

	@NotNull
	@NotBlank(message = "Mobile Number cannot be blank!")
	private String mobileNumber;

	@NotNull
	@NotBlank(message = "Blood Group cannot be blank!")
	@Indexed
	private String bloodGroup;

	@NotNull
	@NotBlank(message = "Gender cannot be blank!")
	@Indexed
	private String gender;

//	@NotNull
//	@NotBlank(message = "Religion cannot be blank!")
//	@Indexed
//	private String religion;

	@NotNull
	@NotBlank(message = "Nationality cannot be blank!")
	@Indexed
	private String nationality;

	@NotNull
	@NotBlank(message = "Father/Spouse Name cannot be blank!")
	private String fatherOrSpouseName;

	@NotNull
	@NotBlank(message = "Email cannot be Blank!")
	@Email(message = "Valid email format is required!")
	@Indexed
	private String email;

	@NotNull
	@NotBlank(message = "Emergency Contact Name cannot be blank!")
	private String emgContactName;

	@NotNull
	@NotBlank(message = "Relation to Emergency Contact cannot be blank!")
	private String emgContactRelation;

	@NotNull
	@NotBlank(message = "Email of Emergency Contact cannot be blank!")
	private String emgContactEmail;

	@NotNull
	@NotBlank(message = "Mobile Number of Emergency Contact cannot be blank!")
	private String emgContactMobile;

	@NotNull
	@NotBlank(message = "Identification Marks cannot be blank!")
	private String socialMediLink;

//	private String hobbies;
//	
//	@NotNull
//	@NotBlank(message = "Identification Marks cannot be blank!")
//	private String identificationMarks;

	@Valid
	private List<Address> addressList;
	@Valid
	private List<Identity> identityList;
	@Valid
	private List<Language> languageList;
	@Valid
	private List<Education> educationList;
	@Valid
	private List<Work> workList;
	@Valid
	private List<Travel> travelList;
	@Valid
	private List<Specialization> specializationList;
	@Valid
	private List<RefPerson> refPersonList;
//	private List<Family> familyList;
//	private List<Certifications> certificationsList;

	private long created;
	private long modified;

	@Indexed
	private String modifiedBy;

	public CandidateDetails() {
		this.id = UUID.randomUUID().toString();
		this.created = Instant.now().toEpochMilli();
		this.modified = Instant.now().toEpochMilli();
	}

	public CandidateDetails(String empId, String firstName, String lastName, long dateOfBirth, long dateOfJoining,
			String maritalStatus, String mobileNumber, String bloodGroup, String gender, String nationality,
			String fatherOrSpouseName, String email, String emgContactName, String emgContactRelation,
			String emgContactEmail, String emgContactMobile, String socialMediLink, String uan,

			List<Address> addressList, List<Identity> identityList, List<Language> languageList,
			List<Education> educationList, List<Work> workList, List<Travel> travelList,
			List<Specialization> specializationList, List<RefPerson> refPersonList, String modifiedBy) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.dateOfJoining = dateOfJoining;
		this.maritalStatus = maritalStatus;
		this.mobileNumber = mobileNumber;
		this.bloodGroup = bloodGroup;
		this.gender = gender;
		this.nationality = nationality;
		this.fatherOrSpouseName = fatherOrSpouseName;
		this.email = email;
		this.emgContactName = emgContactName;
		this.emgContactRelation = emgContactRelation;
		this.emgContactEmail = emgContactEmail;
		this.emgContactMobile = emgContactMobile;
		this.socialMediLink = socialMediLink;
		this.uan = uan;
		this.addressList = addressList;
		this.identityList = identityList;
		this.languageList = languageList;
		this.educationList = educationList;
		this.workList = workList;
		this.travelList = travelList;
		this.specializationList = specializationList;
		this.refPersonList = refPersonList;
		this.modifiedBy = modifiedBy;
	}
	
	public String getUan() {
		return uan;
	}

	public void setUan(String uan) {
		this.uan = uan;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(long dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public long getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(long dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

//	public String getReligion() {
//		return religion;
//	}
//
//	public void setReligion(String religion) {
//		this.religion = religion;
//	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getFatherOrSpouseName() {
		return fatherOrSpouseName;
	}

	public void setFatherOrSpouseName(String fatherOrSpouseName) {
		this.fatherOrSpouseName = fatherOrSpouseName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmgContactName() {
		return emgContactName;
	}

	public void setEmgContactName(String emgContactName) {
		this.emgContactName = emgContactName;
	}

	public String getEmgContactRelation() {
		return emgContactRelation;
	}

	public void setEmgContactRelation(String emgContactRelation) {
		this.emgContactRelation = emgContactRelation;
	}

	public String getEmgContactEmail() {
		return emgContactEmail;
	}

	public void setEmgContactEmail(String emgContactEmail) {
		this.emgContactEmail = emgContactEmail;
	}

	public String getEmgContactMobile() {
		return emgContactMobile;
	}

	public void setEmgContactMobile(String emgContactMobile) {
		this.emgContactMobile = emgContactMobile;
	}

	public String getSocialMediLink() {
		return socialMediLink;
	}

	public void setSocialMediLink(String socialMediLink) {
		this.socialMediLink = socialMediLink;
	}

//	public String getHobbies() {
//		return hobbies;
//	}
//
//	public void setHobbies(String hobbies) {
//		this.hobbies = hobbies;
//	}
//
//	public String getIdentificationMarks() {
//		return identificationMarks;
//	}
//
//	public void setIdentificationMarks(String identificationMarks) {
//		this.identificationMarks = identificationMarks;
//	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public List<Identity> getIdentityList() {
		return identityList;
	}

	public void setIdentityList(List<Identity> identityList) {
		this.identityList = identityList;
	}

	public List<Language> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<Language> languageList) {
		this.languageList = languageList;
	}

	public List<RefPerson> getRefPersonList() {
		return refPersonList;
	}

	public void setRefPersonList(List<RefPerson> refPersonList) {
		this.refPersonList = refPersonList;
	}

	public long getCreated() {
		return created;
	}

	public long getModified() {
		return modified;
	}

	public void setModified(long modified) {
		this.modified = modified;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public List<Education> getEducationList() {
		return educationList;
	}

	public void setEducationList(List<Education> educationList) {
		this.educationList = educationList;
	}

	public List<Work> getWorkList() {
		return workList;
	}

	public void setWorkList(List<Work> workList) {
		this.workList = workList;
	}

//	public List<Family> getFamilyList() {
//		return familyList;
//	}
//
//	public void setFamilyList(List<Family> familyList) {
//		this.familyList = familyList;
//	}

	public List<Travel> getTravelList() {
		return travelList;
	}

	public void setTravelList(List<Travel> travelList) {
		this.travelList = travelList;
	}

	public List<Specialization> getSpecializationList() {
		return specializationList;
	}

	public void setSpecializationList(List<Specialization> specializationList) {
		this.specializationList = specializationList;
	}

//	public List<Certifications> getCertificationsList() {
//		return certificationsList;
//	}
//
//	public void setCertificationsList(List<Certifications> certificationsList) {
//		this.certificationsList = certificationsList;
//	}

}
