package com.example.DecisionEngine;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DecisionEngineApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void loanEngineWorksThroughAllLayers() throws Exception {

		mockMvc.perform(get("/loanDecision/5000/24/49002010998")
				.contentType("application/json")
				.param("amount", "5000")
                .param("months","24")
                .param("code","49002010998"))
				.andExpect(status().isOk())
				.andExpect(content().json("{\n" +
                        "    \"enteredAmount\": 5000.0,\n" +
                        "    \"enteredPeriod\": 24,\n" +
                        "    \"proposedAmount\": 10000.0,\n" +
                        "    \"proposedPeriod\": 24\n" +
                        "}"));



    }


}
