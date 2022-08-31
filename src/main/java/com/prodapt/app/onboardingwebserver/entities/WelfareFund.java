package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("dev-welfare-fund")
public class WelfareFund implements Serializable {

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

	@NotNull
	@NotBlank
	@Indexed
	private String welfareFundAmount;

	@Indexed
	private String empCode;

	@Indexed
	private String prodaptEmail;

	@NotNull
	@Indexed
	private long dateOfJoining;

	@NotNull
	@NotBlank
	private String signImage;

	@NotNull
	private long signDate;

	private long createdOn;

	private long modifiedOn;

	public WelfareFund() {
		this.id = UUID.randomUUID().toString();
		this.createdOn = Instant.now().toEpochMilli();
		this.modifiedOn = Instant.now().toEpochMilli();
	}

	public WelfareFund(@NotNull @NotBlank String empId, @NotNull @NotBlank String empName,
			@NotNull @NotBlank String welfareFundAmount, @NotNull long dateOfJoining, String empCode,
			String prodaptEmail, @NotNull @NotBlank String signImage, @NotNull @NotBlank long signDate) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.welfareFundAmount = welfareFundAmount;
		this.dateOfJoining = dateOfJoining;
		this.empCode = empCode;
		this.prodaptEmail = prodaptEmail;
		this.signImage = signImage;
		this.signDate = signDate;

	}

	public String getSignImage() {
		return signImage;
	}

	public void setSignImage(String signImage) {
		this.signImage = signImage;
	}

	public long getSignDate() {
		return signDate;
	}

	public void setSignDate(long signDate) {
		this.signDate = signDate;
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

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getWelfareFundAmount() {
		return welfareFundAmount;
	}

	public void setWelfareFundAmount(String welfareFundAmount) {
		this.welfareFundAmount = welfareFundAmount;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getProdaptEmail() {
		return prodaptEmail;
	}

	public void setProdaptEmail(String prodaptEmail) {
		this.prodaptEmail = prodaptEmail;
	}

	public long getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(long dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(long createdOn) {
		this.createdOn = createdOn;
	}

	public long getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(long modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

}
