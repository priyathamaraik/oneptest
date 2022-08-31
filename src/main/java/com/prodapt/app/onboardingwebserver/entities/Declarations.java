package com.prodapt.app.onboardingwebserver.entities;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("dev-declarations")
public class Declarations {
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
	
	private List<Document> documentsList;
	
	@NotNull
	@NotBlank
	@Indexed
	private String email;
	
	private long createdOn;
	
	private long modifiedOn;

	public Declarations() {
		this.id = UUID.randomUUID().toString();
		this.createdOn = Instant.now().toEpochMilli();
		this.modifiedOn = Instant.now().toEpochMilli();
	}

	public Declarations(@NotNull @NotBlank String empId, @NotNull @NotBlank String empName,
			List<Document> documentsList, @NotNull @NotBlank String email) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.documentsList = documentsList;
		this.email = email;
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

	public List<Document> getDocumentsList() {
		return documentsList;
	}

	public String getEmail() {
		return email;
	}

	public long getCreatedOn() {
		return createdOn;
	}

	public long getModifiedOn() {
		return modifiedOn;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public void setDocumentsList(List<Document> documentsList) {
		this.documentsList = documentsList;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setModifiedOn(long modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
		
	
}
