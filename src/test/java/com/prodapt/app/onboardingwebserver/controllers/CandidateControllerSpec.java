package com.prodapt.app.onboardingwebserver.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prodapt.app.onboardingwebserver.entities.JwtRequest;


@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Testing Candidate Controller =>")
class CandidateControllerSpec {
	
	@Autowired
	MockMvc mockMvc;
	
	private String jwt;
	
	@BeforeAll
	@DisplayName("Auth and retrieve jwt")
	void auth() throws Exception {
		ResultActions resultActions = mockMvc.perform(
				post("/auth")
				.content(asJsonString(new JwtRequest("admin", "password")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
		
		String result = resultActions.andReturn().getResponse().getContentAsString();
		JacksonJsonParser jsonParser = new JacksonJsonParser();
		jwt = jsonParser.parseMap(result).get("jwtToken").toString();
	}
	
	String asJsonString(JwtRequest jwtRequest) throws Exception {
		return new ObjectMapper().writeValueAsString(jwtRequest);
	}
	

	@Test
	@DisplayName("Get All Candidates in DB")
	void getAllCandidatesSpec() throws Exception {
		mockMvc
		.perform(
				get("/candidate/get/all")
				.header("Authorization", "Bearer " + jwt)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	@DisplayName("Get Candidate by ID")
	void getCandidateById() throws Exception {
		mockMvc
			.perform(
					get("/candidate/get/{id}", "2a7fe683-772a-4dde-bd25-b457ed971a40")
					.header("Authorization", "Bearer " + jwt)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					)
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}
	
	
}
