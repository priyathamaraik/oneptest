package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;
import org.springframework.data.annotation.Id;

@RedisHash("dev-mediclaim")
public class Mediclaim implements Serializable {

	private static final long serialVersionUID = -6621470205939974159L;

	@Id
	private String id;

	@NotBlank(message = "Name cannot be blank!")
	@NotNull
	@Indexed
	private String name;

	@NotBlank(message = "Emp Id cannot be blank!")
	@NotNull
	@Indexed
	private String empId;

	@Indexed
	private String empCode;

	@Indexed
	private String prodaptEmail;

	@NotBlank(message = "Gender cannot be blank!")
	@NotNull
	@Indexed
	private String gender;

	@Indexed
	private long dateOfJoining;

	@NotNull
	@Indexed
	private long dateOfBirth;

	@NotBlank(message = "Covid Vaccination cannot be blank!")
	@NotNull
	@Indexed
	private String covidVaccination;

	private List<Relation> relationList;

	private long created;
	private long modified;

	@Indexed
	private String modifiedBy;

	public Mediclaim() {
		this.id = UUID.randomUUID().toString();
		this.created = Instant.now().toEpochMilli();
		this.modified = Instant.now().toEpochMilli();
	}

	public Mediclaim(String name, String empId, String empCode, String gender, long dateOfJoining, long dateOfBirth,
			String covidVaccination, List<Relation> relationList) {
		super();
		this.name = name;
		this.empId = empId;
		this.empCode = empCode;
		this.gender = gender;
		this.dateOfJoining = dateOfJoining;
		this.dateOfBirth = dateOfBirth;
		this.covidVaccination = covidVaccination;
		this.relationList = relationList;
	}

	
	public String getCovidVaccination() {
		return covidVaccination;
	}

	public void setCovidVaccination(String covidVaccination) {
		this.covidVaccination = covidVaccination;
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

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(long dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public long getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(long dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<Relation> getRelationList() {
		return relationList;
	}

	public void setRelationList(List<Relation> relationList) {
		this.relationList = relationList;
	}

}
