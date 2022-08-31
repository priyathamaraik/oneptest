package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("dev-home-doc")
public class HomeDoc implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5725734789597147776L;

	@Id
	private String id;
	
	@NotNull
	@NotBlank
	@Indexed
	private String docName;
	
	@NotNull
	@NotBlank
	private String docUrl;

	public HomeDoc() {
		this.id = UUID.randomUUID().toString();
	}

	public HomeDoc(@NotNull @NotBlank String docName, @NotNull @NotBlank String docUrl) {
		super();
		this.docName = docName;
		this.docUrl = docUrl;
	}

	public String getId() {
		return id;
	}

	public String getDocName() {
		return docName;
	}

	public String getDocUrl() {
		return docUrl;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public void setDocUrl(String docUrl) {
		this.docUrl = docUrl;
	}
	
	
	
}
