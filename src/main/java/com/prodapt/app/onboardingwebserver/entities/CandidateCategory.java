package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("dev-candidate-category")
public class CandidateCategory implements Serializable {

	private static final long serialVersionUID = 5964391725120103566L;

	@Id
	private String id;

	@NotNull
	@NotBlank(message = "Name type cannot be blank!")
	private String categoryName;

	public CandidateCategory() {
		this.id = UUID.randomUUID().toString();
	}

	public CandidateCategory(String categoryName) {
		super();
		this.categoryName = categoryName;
	}

	public String getId() {
		return id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}