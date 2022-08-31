package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("dev-link")
public class Link implements Serializable {

	private static final long serialVersionUID = 5964391725120103566L;

	@Id
	private String id;

	@NotNull
	@NotBlank(message = "Link Name cannot be blank!")
	@Indexed
	private String linkName;

	@NotNull
	@NotBlank(message = "Add Link cannot be blank!")
	@Indexed
	private String addLink;

	public Link() {
		this.id = UUID.randomUUID().toString();
	}

	public Link(String linkName, String addLink) {
		super();
		this.linkName = linkName;
		this.addLink = addLink;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getAddLink() {
		return addLink;
	}

	public void setAddLink(String addLink) {
		this.addLink = addLink;
	}

}