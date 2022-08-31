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

@RedisHash("dev-gratuity")
public class Gratuity implements Serializable {

	private static final long serialVersionUID = 1026918567788006746L;

	@Id
	private String id;

	@NotNull
	@NotBlank(message = "Emp Id cannot be blank!")
	@Indexed
	private String empId;

	@NotNull
	@NotBlank(message = "Emp Name cannot be blank!")
	@Indexed
	private String empName;

	@Indexed
	private String empCode;

	@Indexed
	private String prodaptEmail;

	@NotNull
	@NotBlank(message = "Company Address cannot be blank!")
	private String companyAddress;

	@NotNull
	@NotBlank(message = "Emp Gender cannot be blank!")
	@Indexed
	private String gender;

	@NotNull
	@NotBlank(message = "Emp Religion cannot be blank!")
	@Indexed
	private String religion;

	@NotNull
	@NotBlank(message = "Emp Maritial Status cannot be blank!")
	@Indexed
	private String maritialStatus;

	@Indexed
	private String empDept;

	@Indexed
	private long dateOfAppointment;

	@Indexed
	private String empTicketOrSerialNo;

	private String empVillage;
	private String empThana;
	private String empSubDivision;
	private String empPostOffice;
	@NotNull
	@NotBlank
	private String empDistrict;

	@NotNull
	@NotBlank
	private String empState;

	@NotNull
	@NotBlank
	private String empAddress;

	private List<GratuityNominee> nomineeList;

	private String witnessPlace;
	private String witnessName;
	private String witnessAddress;
	private long witnessDate;

	private long dateOn;
	private String placeOn;
	private String formfSignImage;

	private long ackDate;
	private String ackSignImage;

	private long createdOn;
	private long modifiedOn;
	private String modifiedBy;

	public Gratuity() {
		this.id = UUID.randomUUID().toString();
		this.createdOn = Instant.now().toEpochMilli();
		this.modifiedOn = Instant.now().toEpochMilli();
	}

	public Gratuity(String empId, String empName, String companyAddress, String gender, String religion,
			String maritialStatus, String empDept, long dateOfAppointment, String empTicketOrSerialNo,
			String empVillage, String empThana, String empSubDivision, String empPostOffice, String empDistrict,
			String empState, String empAddress, List<GratuityNominee> nomineeList, String witnessId, String witnessName,
			String witnessAddress, long witnessDate, long dateOn, String placeOn, String formfSignImage, long ackDate,
			String ackSignImage, String modifiedBy) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.companyAddress = companyAddress;
		this.gender = gender;
		this.religion = religion;
		this.maritialStatus = maritialStatus;
		this.empDept = empDept;
		this.dateOfAppointment = dateOfAppointment;
		this.empTicketOrSerialNo = empTicketOrSerialNo;
		this.empVillage = empVillage;
		this.empThana = empThana;
		this.empSubDivision = empSubDivision;
		this.empPostOffice = empPostOffice;
		this.empDistrict = empDistrict;
		this.empState = empState;
		this.empAddress = empAddress;
		this.nomineeList = nomineeList;
		this.witnessPlace = witnessId;
		this.witnessName = witnessName;
		this.witnessAddress = witnessAddress;
		this.witnessDate = witnessDate;
		this.dateOn = dateOn;
		this.placeOn = placeOn;
		this.formfSignImage = formfSignImage;
		this.ackDate = ackDate;
		this.ackSignImage = ackSignImage;
		this.modifiedBy = modifiedBy;
	}

	public String getFormfSignImage() {
		return formfSignImage;
	}

	public void setFormfSignImage(String formfSignImage) {
		this.formfSignImage = formfSignImage;
	}

	public long getAckDate() {
		return ackDate;
	}

	public void setAckDate(long ackDate) {
		this.ackDate = ackDate;
	}

	public String getAckSignImage() {
		return ackSignImage;
	}

	public void setAckSignImage(String ackSignImage) {
		this.ackSignImage = ackSignImage;
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

	public String getEmpId() {
		return empId;
	}

	public String getEmpName() {
		return empName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public String getGender() {
		return gender;
	}

	public String getReligion() {
		return religion;
	}

	public String getMaritialStatus() {
		return maritialStatus;
	}

	public String getEmpDept() {
		return empDept;
	}

	public long getDateOfAppointment() {
		return dateOfAppointment;
	}

	public String getEmpTicketOrSerialNo() {
		return empTicketOrSerialNo;
	}

	public String getEmpVillage() {
		return empVillage;
	}

	public String getEmpThana() {
		return empThana;
	}

	public String getEmpSubDivision() {
		return empSubDivision;
	}

	public String getEmpPostOffice() {
		return empPostOffice;
	}

	public String getEmpDistrict() {
		return empDistrict;
	}

	public String getEmpState() {
		return empState;
	}

	public String getEmpAddress() {
		return empAddress;
	}

	public List<GratuityNominee> getNomineeList() {
		return nomineeList;
	}

	public String getWitnessPlace() {
		return witnessPlace;
	}

	public String getWitnessName() {
		return witnessName;
	}

	public String getWitnessAddress() {
		return witnessAddress;
	}

	public long getWitnessDate() {
		return witnessDate;
	}

	public long getDateOn() {
		return dateOn;
	}

	public String getPlaceOn() {
		return placeOn;
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

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public void setMaritialStatus(String maritialStatus) {
		this.maritialStatus = maritialStatus;
	}

	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}

	public void setDateOfAppointment(long dateOfAppointment) {
		this.dateOfAppointment = dateOfAppointment;
	}

	public void setEmpTicketOrSerialNo(String empTicketOrSerialNo) {
		this.empTicketOrSerialNo = empTicketOrSerialNo;
	}

	public void setEmpVillage(String empVillage) {
		this.empVillage = empVillage;
	}

	public void setEmpThana(String empThana) {
		this.empThana = empThana;
	}

	public void setEmpSubDivision(String empSubDivision) {
		this.empSubDivision = empSubDivision;
	}

	public void setEmpPostOffice(String empPostOffice) {
		this.empPostOffice = empPostOffice;
	}

	public void setEmpDistrict(String empDistrict) {
		this.empDistrict = empDistrict;
	}

	public void setEmpState(String empState) {
		this.empState = empState;
	}

	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}

	public void setNomineeList(List<GratuityNominee> nomineeList) {
		this.nomineeList = nomineeList;
	}

	public void setWitnessPlace(String witnessId) {
		this.witnessPlace = witnessId;
	}

	public void setWitnessName(String witnessName) {
		this.witnessName = witnessName;
	}

	public void setWitnessAddress(String witnessAddress) {
		this.witnessAddress = witnessAddress;
	}

	public void setWitnessDate(long witnessDate) {
		this.witnessDate = witnessDate;
	}

	public void setDateOn(long dateOn) {
		this.dateOn = dateOn;
	}

	public void setPlaceOn(String placeOn) {
		this.placeOn = placeOn;
	}

	public void setModifiedOn(long modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
