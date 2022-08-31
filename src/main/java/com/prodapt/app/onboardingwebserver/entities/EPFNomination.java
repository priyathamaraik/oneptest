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

@RedisHash("dev-epf-nomination")
public class EPFNomination implements Serializable {

	private static final long serialVersionUID = -56394141071807428L;

	@Id
	private String id;
	
	@NotNull
	@NotBlank(message = "Emp ID cannot be blank!")
	@Indexed
	private String empId;
	
	@Indexed
	private String empCode;
	
	@Indexed
	private String prodaptEmail;
	
	@NotNull
	@NotBlank(message = "Emp Name cannot be blank!")
	@Indexed
	private String empName;
	
	@NotNull
	@NotBlank(message = "Emp Father/Spouse Name cannot be blank!")
	@Indexed
	private String empFatherOrSpouseName;
	
	@NotNull
	private long dateOfBirth;
	
	@NotNull
	@NotNull(message = "Gender cannot be blank!")
	@Indexed
	private String gender;
	
	@NotNull
	@NotNull(message = "Maritial Status cannot be blank!")
	@Indexed
	private String maritialStatus;
	
	private String accountNumber;
	
	@NotNull
	private long dateOfJoining;
	
	@NotNull
	@NotNull(message = "Permanent Address cannot be blank!")
	@Indexed
	private String permanentAddress;
	
	@NotNull
	@NotNull(message = "Present Address cannot be blank!")
	@Indexed
	private String presentAddress;
	
	private String epfNomineeSign;
	private String epsNomineeSign;
	private long epsNomineeDate;
	
	private List<EPFNominee> epfNomineeList;
	private List<EPSFamily> epsFamilyList;
	private List<EPFNominee> nomineeList;
	
	private long createdOn;
	private long modifiedOn;
	
	private String modifiedBy;
	
	public EPFNomination() {
		this.id = UUID.randomUUID().toString();
		this.createdOn = Instant.now().toEpochMilli();
		this.modifiedOn = Instant.now().toEpochMilli();
	}

	public EPFNomination(String empId, String empName, String empFatherOrSpouseName, long dateOfBirth, String gender,
			String maritialStatus, String accountNumber, long dateOfJoining, String permanentAddress,String epfNomineeSign, 
			String epsNomineeSign, long epsNomineeDate,
			String presentAddress, List<EPFNominee> epfNomineeList, List<EPSFamily> epsFamilyList,
			List<EPFNominee> nomineeList, String modifiedBy) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empFatherOrSpouseName = empFatherOrSpouseName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.maritialStatus = maritialStatus;
		this.accountNumber = accountNumber;
		this.dateOfJoining = dateOfJoining;
		this.permanentAddress = permanentAddress;
		this.presentAddress = presentAddress;
		this.epfNomineeSign = epfNomineeSign;
		this.epsNomineeSign = epsNomineeSign;
		this.epsNomineeDate = epsNomineeDate;
		this.epfNomineeList = epfNomineeList;
		this.epsFamilyList = epsFamilyList;
		this.nomineeList = nomineeList;
		this.modifiedBy = modifiedBy;
	}


	
	public String getEpfNomineeSign() {
		return epfNomineeSign;
	}

	public void setEpfNomineeSign(String epfNomineeSign) {
		this.epfNomineeSign = epfNomineeSign;
	}

	public String getEpsNomineeSign() {
		return epsNomineeSign;
	}

	public void setEpsNomineeSign(String epsNomineeSign) {
		this.epsNomineeSign = epsNomineeSign;
	}

	public long getEpsNomineeDate() {
		return epsNomineeDate;
	}

	public void setEpfNomineeDate(long epsNomineeDate) {
		this.epsNomineeDate = epsNomineeDate;
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

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public String getEmpFatherOrSpouseName() {
		return empFatherOrSpouseName;
	}

	public long getDateOfBirth() {
		return dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public String getMaritialStatus() {
		return maritialStatus;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public long getDateOfJoining() {
		return dateOfJoining;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public String getPresentAddress() {
		return presentAddress;
	}

	public List<EPFNominee> getEpfNomineeList() {
		return epfNomineeList;
	}

	public List<EPSFamily> getEpsFamilyList() {
		return epsFamilyList;
	}

	public List<EPFNominee> getNomineeList() {
		return nomineeList;
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

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public void setEmpFatherOrSpouseName(String empFatherOrSpouseName) {
		this.empFatherOrSpouseName = empFatherOrSpouseName;
	}

	public void setDateOfBirth(long dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setMaritialStatus(String maritialStatus) {
		this.maritialStatus = maritialStatus;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setDateOfJoining(long dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}

	public void setEpfNomineeList(List<EPFNominee> epfNomineeList) {
		this.epfNomineeList = epfNomineeList;
	}

	public void setEpsFamilyList(List<EPSFamily> epsFamilyList) {
		this.epsFamilyList = epsFamilyList;
	}

	public void setNomineeList(List<EPFNominee> nomineeList) {
		this.nomineeList = nomineeList;
	}

	public void setModifiedOn(long modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
}
