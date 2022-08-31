package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("dev-kyc-detail")
public class KYCDetail implements Serializable {

	private static final long serialVersionUID = 5598115434892120013L;

	@Id
	private String id;
	
	private String selectKYC;
	private String nameAsPerDoc;
	private String docNumber;
	private String remarks;
	
	public KYCDetail() {
		this.id = UUID.randomUUID().toString();
	}

	public KYCDetail(String selectKYC, String nameAsPerDoc, String docNumber, String remarks) {
		super();
		this.selectKYC = selectKYC;
		this.nameAsPerDoc = nameAsPerDoc;
		this.docNumber = docNumber;
		this.remarks = remarks;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public String getSelectKYC() {
		return selectKYC;
	}

	public String getNameAsPerDoc() {
		return nameAsPerDoc;
	}

	public String getDocNumber() {
		return docNumber;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setSelectKYC(String selectKYC) {
		this.selectKYC = selectKYC;
	}

	public void setNameAsPerDoc(String nameAsPerDoc) {
		this.nameAsPerDoc = nameAsPerDoc;
	}

	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
