package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("dev-language")
public class Language implements Serializable {

	private static final long serialVersionUID = 8204686804927913035L;
	
	@Id
	private String id;
	
	@NotNull
	@NotBlank(message = "Language Name cannot be blank!")
	private String name;
	
	private boolean read = false;
	private boolean write = false;
	private boolean speak = false;
		
	public Language() {
		this.id = UUID.randomUUID().toString();	
	}

	public Language(String name, 
			boolean read,
			boolean write, 
			boolean speak) {
		super();
		this.name = name;
		this.read = read;
		this.write = write;
		this.speak = speak;
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

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public boolean isWrite() {
		return write;
	}

	public void setWrite(boolean write) {
		this.write = write;
	}

	public boolean isSpeak() {
		return speak;
	}

	public void setSpeak(boolean speak) {
		this.speak = speak;
	}

}
