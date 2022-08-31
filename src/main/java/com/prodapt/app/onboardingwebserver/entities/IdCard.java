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


@RedisHash("dev-idcard")
public class IdCard implements Serializable {
	
	private static final long serialVersionUID = -7873634905029709220L;
	
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
	
	private String address;
	
	@Indexed
	private String bloodGroup; 
	
	private String mobile; 
	private List<Vehicle> vehicleDetails; 
	private String photoImage; 
	private String signImage;
	private long created;
	private long modified;
	
	@Indexed
	private String modifiedBy;
	
	public IdCard() {
		this.id = UUID.randomUUID().toString();
		this.created = Instant.now().toEpochMilli();
		this.modified = Instant.now().toEpochMilli();
	}
	
	public IdCard(String empId, 
			String empName, 
			String empCode, 
			String address, 
			String bloodGroup, 
			String mobile,
			List<Vehicle> vehicleDetails, 
			String photoImage, 
			String signImage, 
			String modifiedBy) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empCode = empCode;
		this.address = address;
		this.bloodGroup = bloodGroup;
		this.mobile = mobile;
		this.vehicleDetails = vehicleDetails;
		this.photoImage = photoImage;
		this.signImage = signImage;
		this.modifiedBy = modifiedBy;
		
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
	
	public String getEmpCode() {
		return empCode;
	}
	
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getBloodGroup() {
		return bloodGroup;
	}
	
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public List<Vehicle> getVehicleDetails() {
		return vehicleDetails;
	}
	
	public void setVehicleDetails(List<Vehicle> vehicleDetails) {
		this.vehicleDetails = vehicleDetails;
	}
	
	public String getPhotoImage() {
		return photoImage;
	}
	
	public void setPhotoImage(String photoImage) {
		this.photoImage = photoImage;
	}
	
	public String getSignImage() {
		return signImage;
	}
	
	public void setSignImage(String signImage) {
		this.signImage = signImage;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}


