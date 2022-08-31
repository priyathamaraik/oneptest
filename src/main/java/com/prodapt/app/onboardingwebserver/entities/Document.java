package com.prodapt.app.onboardingwebserver.entities;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("dev-document")
public class Document {
	
	@Id
	private String id;
	
	@NotNull
	@NotBlank
	@Indexed
	private String docName;
	
	@NotNull
	@NotBlank
	@Indexed
	private String docDesc;
	
	@NotNull
	@NotBlank
	private String signImage;
	
	@NotNull
	private long signDate;

	public Document() {
		this.id = UUID.randomUUID().toString();
	}

	public Document(@NotNull @NotBlank String docName, @NotNull @NotBlank String docDesc,
			@NotNull @NotBlank String signImage, @NotNull @NotBlank long signDate) {
		super();
		this.docName = docName;
		this.docDesc = docDesc;
		this.signImage = signImage;
		this.signDate = signDate;
	}

	public String getId() {
		return id;
	}

	public String getDocName() {
		return docName;
	}

	public String getDocDesc() {
		return docDesc;
	}

	public String getSignImage() {
		return signImage;
	}

	public long getSignDate() {
		return signDate;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public void setDocDesc(String docDesc) {
		this.docDesc = docDesc;
	}

	public void setSignImage(String signImage) {
		this.signImage = signImage;
	}

	public void setSignDate(long signDate) {
		this.signDate = signDate;
	}
	

}
