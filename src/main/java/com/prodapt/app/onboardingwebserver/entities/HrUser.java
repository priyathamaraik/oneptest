package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("dev-hr-user")
public class HrUser implements Serializable {

	private static final long serialVersionUID = -8526077044845837014L;
	
	@Id
	private String id;
	
	@Email(message = "Valid email is required!")
	@NotBlank(message = "email cannot be blank!")
	@NotNull
	@Indexed
	private String email;
	
	@NotBlank(message = "Emp Code cannot be blank!")
	@NotNull
	@Indexed
	private String empCode;
	
	@NotNull
	@NotBlank(message = "Name cannot be blank!")
	@Indexed
	private String name;
	
	@Indexed
	private String country;
	
	@Indexed
	private String region;
	
	private String password = "123456";
	
	@Indexed
	private String role = "HR-USER";
	
	@Indexed
	private boolean enabled = true;
	private long created;
	private long modified;
	
	public HrUser() {
		this.id = UUID.randomUUID().toString();
		this.created = Instant.now().toEpochMilli();
		this.modified = Instant.now().toEpochMilli();
	}
	
	public HrUser(String email, String name, String password, String empCode) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.empCode = empCode;
	}
	
	
	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
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

}
