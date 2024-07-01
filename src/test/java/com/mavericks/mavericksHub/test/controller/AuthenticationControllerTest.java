package com.mavericks.mavericksHub.test.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavericks.mavericksHub.dtos.request.LoginRequest;
import com.mavericks.mavericksHub.security.filter.CustomerUsernameAndPasswordAuthFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"/db/data.sql"})
public class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void autheticateUserTest() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("email@gmail.com");
        loginRequest.setPassword("password");
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/api/v1/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(loginRequest)))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    public void authenticateUserWithInvalidUsernameTest() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("emaill@gmail.com");
        loginRequest.setPassword("password");
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/api/v1/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(loginRequest)))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }


}
