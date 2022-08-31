package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("dev-epfkyc-details")
public class EPFKYCDetails implements Serializable {

	private static final long serialVersionUID = 8209715348156994208L;

	@Id
	private String id;

	@NotNull
	@NotBlank
	@Indexed
	private String empId;

	@Indexed
	private String empCode;

	@Indexed
	private String prodaptEmail;

	@NotNull
	@NotBlank
	@Indexed
	private String name;

	@NotNull
	@NotBlank
	@Indexed
	private String mobile;

	@NotNull
	@Indexed
	private long dateOfBirth;

	@NotNull
	@Indexed
	private long dateOfJoining;

	@NotNull
	@NotBlank
	@Indexed
	private String email;

	@NotNull
	@NotBlank
	@Indexed
	private String gender;

	@NotNull
	@NotBlank
	@Indexed
	private String fatherOrSpouseName;

	@NotNull
	@NotBlank
	@Indexed
	private String relationship;

	@NotNull
	@NotBlank
	@Indexed
	private String memberOfEPF;

	@NotNull
	@NotBlank
	@Indexed
	private String memberOfEPS;

	@Indexed
	private String uan;

	private String regionCode;
	private String officeCode;
	private String establishmentId;
	private String extension;
	private String accountNumber;
	private long dateOfExit;
	private long dateOfJoiningPrev;
	private String schemeCertificateNumber;
	private String ppoNumber;

	@NotNull
	@NotBlank
	private String isInternationWorker;
	private String countryOforigin;
	private String passportNumber;
	private long validFrom;
	private long validTo;

	@NotNull
	@NotBlank
	private String eduQualifications;

	@NotNull
	@NotBlank
	private String maritialStatus;

	@NotNull
	@NotBlank
	private String speciallyabled;
	private String disabledCategory;

	private long epfEnrolledDate;
	private String epfWages;
	private String epfMember;
	private String epfWithdrawn;
	private String epsWithdrawn;
	private String epsEarned;

	private List<KYCDetail> kycDetailList;
	private String placeOfConsent;
	private long dateOfConsent;
	private String epfSignImage;

	private long createdOn;
	private long modifiedOn;
	private String modifiedBy;

	public EPFKYCDetails() {
		this.id = UUID.randomUUID().toString();
		this.createdOn = Instant.now().toEpochMilli();
		this.modifiedOn = Instant.now().toEpochMilli();
	}

	public EPFKYCDetails(String empId, String name, String empCode, String mobile, long dateOfBirth, long dateOfJoining,
			String email, String gender, String fatherOrSpouseName, String relationship, String memberOfEPF,
			String memberOfEPS, String uan, String regionCode, String officeCode, String establishmentId,
			String extension, String accountNumber, long dateOfExit, long dateOfJoiningPrev,
			String schemeCertificateNumber, String ppoNumber, String isInternationWorker, String countryOforigin,
			String passportNumber, long validFrom, long validTo, String eduQualifications, String maritialStatus,
			String speciallyabled, String disabledCategory, long epfEnrolledDate, String epfWages, String epfMember,
			String epfWithdrawn, String epsWithdrawn, String epsEarned, List<KYCDetail> kycDetailList,
			String placeOfConsent, long dateOfConsent, String epfSignImage, String modifiedBy) {
		super();
		this.empId = empId;
		this.name = name;
		this.empCode = empCode;
		this.mobile = mobile;
		this.dateOfBirth = dateOfBirth;
		this.dateOfJoining = dateOfJoining;
		this.email = email;
		this.gender = gender;
		this.fatherOrSpouseName = fatherOrSpouseName;
		this.relationship = relationship;
		this.memberOfEPF = memberOfEPF;
		this.memberOfEPS = memberOfEPS;
		this.uan = uan;
		this.regionCode = regionCode;
		this.officeCode = officeCode;
		this.establishmentId = establishmentId;
		this.extension = extension;
		this.accountNumber = accountNumber;
		this.dateOfExit = dateOfExit;
		this.dateOfJoiningPrev = dateOfJoiningPrev;
		this.schemeCertificateNumber = schemeCertificateNumber;
		this.ppoNumber = ppoNumber;
		this.isInternationWorker = isInternationWorker;
		this.countryOforigin = countryOforigin;
		this.passportNumber = passportNumber;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.eduQualifications = eduQualifications;
		this.maritialStatus = maritialStatus;
		this.speciallyabled = speciallyabled;
		this.disabledCategory = disabledCategory;
		this.epfEnrolledDate = epfEnrolledDate;
		this.epfWages = epfWages;
		this.epfMember = epfMember;
		this.epfWithdrawn = epfWithdrawn;
		this.epsWithdrawn = epsWithdrawn;
		this.epsEarned = epsEarned;
		this.kycDetailList = kycDetailList;
		this.placeOfConsent = placeOfConsent;
		this.dateOfConsent = dateOfConsent;
		this.epfSignImage = epfSignImage;
		this.modifiedBy = modifiedBy;
	}

	public long getEpfEnrolledDate() {
		return epfEnrolledDate;
	}

	public void setEpfEnrolledDate(long epfEnrolledDate) {
		this.epfEnrolledDate = epfEnrolledDate;
	}

	public String getEpfWages() {
		return epfWages;
	}

	public void setEpfWages(String epfWages) {
		this.epfWages = epfWages;
	}

	public String getEpfMember() {
		return epfMember;
	}

	public void setEpfMember(String epfMember) {
		this.epfMember = epfMember;
	}

	public String getEpfWithdrawn() {
		return epfWithdrawn;
	}

	public void setEpfWithdrawn(String epfWithdrawn) {
		this.epfWithdrawn = epfWithdrawn;
	}

	public String getEpsWithdrawn() {
		return epsWithdrawn;
	}

	public void setEpsWithdrawn(String epsWithdrawn) {
		this.epsWithdrawn = epsWithdrawn;
	}

	public String getEpsEarned() {
		return epsEarned;
	}

	public void setEpsEarned(String epsEarned) {
		this.epsEarned = epsEarned;
	}

	public String getEpfSignImage() {
		return epfSignImage;
	}

	public void setEpfSignImage(String epfSignImage) {
		this.epfSignImage = epfSignImage;
	}

	public String getProdaptEmail() {
		return prodaptEmail;
	}

	public void setProdaptEmail(String prodaptEmail) {
		this.prodaptEmail = prodaptEmail;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public long getCreatedOn() {
		return createdOn;
	}

	public long getModifiedOn() {
		return modifiedOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setCreatedOn(long createdOn) {
		this.createdOn = createdOn;
	}

	public void setModifiedOn(long modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getId() {
		return id;
	}

	public String getEmpId() {
		return empId;
	}

	public String getName() {
		return name;
	}

	public String getMobile() {
		return mobile;
	}

	public long getDateOfBirth() {
		return dateOfBirth;
	}

	public long getDateOfJoining() {
		return dateOfJoining;
	}

	public String getEmail() {
		return email;
	}

	public String getGender() {
		return gender;
	}

	public String getFatherOrSpouseName() {
		return fatherOrSpouseName;
	}

	public String getRelationship() {
		return relationship;
	}

	public String getMemberOfEPF() {
		return memberOfEPF;
	}

	public String getMemberOfEPS() {
		return memberOfEPS;
	}

	public String getuan() {
		return uan;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public String getEstablishmentId() {
		return establishmentId;
	}

	public String getExtension() {
		return extension;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public long getDateOfExit() {
		return dateOfExit;
	}

	public long getDateOfJoiningPrev() {
		return dateOfJoiningPrev;
	}

	public String getSchemeCertificateNumber() {
		return schemeCertificateNumber;
	}

	public String getPpoNumber() {
		return ppoNumber;
	}

	public String getIsInternationWorker() {
		return isInternationWorker;
	}

	public String getCountryOforigin() {
		return countryOforigin;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public long getValidFrom() {
		return validFrom;
	}

	public long getValidTo() {
		return validTo;
	}

	public String getEduQualifications() {
		return eduQualifications;
	}

	public String getMaritialStatus() {
		return maritialStatus;
	}

	public String getSpeciallyabled() {
		return speciallyabled;
	}

	public String getDisabledCategory() {
		return disabledCategory;
	}

	public List<KYCDetail> getKycDetailList() {
		return kycDetailList;
	}

	public String getPlaceOfConsent() {
		return placeOfConsent;
	}

	public long getDateOfConsent() {
		return dateOfConsent;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setDateOfBirth(long dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setDateOfJoining(long dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setFatherOrSpouseName(String fatherOrSpouseName) {
		this.fatherOrSpouseName = fatherOrSpouseName;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public void setMemberOfEPF(String memberOfEPF) {
		this.memberOfEPF = memberOfEPF;
	}

	public void setMemberOfEPS(String memberOfEPS) {
		this.memberOfEPS = memberOfEPS;
	}

	public void setuan(String uan) {
		this.uan = uan;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public void setEstablishmentId(String establishmentId) {
		this.establishmentId = establishmentId;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setDateOfExit(long dateOfExit) {
		this.dateOfExit = dateOfExit;
	}

	public void setDateOfJoiningPrev(long dateOfJoiningPrev) {
		this.dateOfJoiningPrev = dateOfJoiningPrev;
	}

	public void setSchemeCertificateNumber(String schemeCertificateNumber) {
		this.schemeCertificateNumber = schemeCertificateNumber;
	}

	public void setPpoNumber(String ppoNumber) {
		this.ppoNumber = ppoNumber;
	}

	public void setIsInternationWorker(String isInternationWorker) {
		this.isInternationWorker = isInternationWorker;
	}

	public void setCountryOforigin(String countryOforigin) {
		this.countryOforigin = countryOforigin;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public void setValidFrom(long validFrom) {
		this.validFrom = validFrom;
	}

	public void setValidTo(long validTo) {
		this.validTo = validTo;
	}

	public void setEduQualifications(String eduQualifications) {
		this.eduQualifications = eduQualifications;
	}

	public void setMaritialStatus(String maritialStatus) {
		this.maritialStatus = maritialStatus;
	}

	public void setSpeciallyabled(String speciallyabled) {
		this.speciallyabled = speciallyabled;
	}

	public void setDisabledCategory(String disabledCategory) {
		this.disabledCategory = disabledCategory;
	}

	public void setKycDetailList(List<KYCDetail> kycDetailList) {
		this.kycDetailList = kycDetailList;
	}

	public void setPlaceOfConsent(String placeOfConsent) {
		this.placeOfConsent = placeOfConsent;
	}

	public void setDateOfConsent(long dateOfConsent) {
		this.dateOfConsent = dateOfConsent;
	}

}
