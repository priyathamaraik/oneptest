package com.prodapt.app.onboardingwebserver.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("dev-faq")
public class FAQ implements Serializable {

	private static final long serialVersionUID = 5964391725120103566L;

	@Id
	private String id;

	@NotNull
	@NotBlank
	@Indexed
	private String cardName;

	@NotNull
	@NotBlank
	private String question;

	@NotNull
	@NotBlank
	private String answer;

	@Indexed
	private boolean importantFAQ = false;

	public FAQ() {
		this.id = UUID.randomUUID().toString();
	}

	public FAQ(String question, String answer, boolean importantFAQ) {
		super();
		this.question = question;
		this.answer = answer;
		this.importantFAQ = importantFAQ;
	}

	public boolean isImportantFAQ() {
		return importantFAQ;
	}

	public void setImportantFaq(boolean importantFAQ) {
		this.importantFAQ = importantFAQ;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
