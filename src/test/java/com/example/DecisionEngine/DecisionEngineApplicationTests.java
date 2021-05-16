package com.example.DecisionEngine;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DecisionEngineApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void loanEngineWorksThroughAllLayers() throws Exception {

//		mockMvc.perform(get("/test/exception_test...")
//				.contentType("application/json")
//				.param("getLoanDecision", " "))
//				.andExpect(status().isOk())
//				.andExpect(content().object(LoanResponse.class));


	}

}
