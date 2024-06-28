package com.mavericks.mavericksHub.test.services;

import com.mavericks.mavericksHub.dtos.request.CreateUserRequest;
import com.mavericks.mavericksHub.dtos.responses.CreateUserResponse;
import com.mavericks.mavericksHub.models.User;
import com.mavericks.mavericksHub.services.interfaces.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class UserServicesTest {
    @Autowired
    private UserService userService;
    @Test
    void createUserTest(){
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setEmail("email21@gmail.com");
        userRequest.setPassword("password");
        CreateUserResponse response = userService.register(userRequest);
        assertNotNull(response);
        assertTrue(response.getMessage().contains("success"));
    }
    @Test
    @DisplayName("test user can be retrieved")
    @Sql(scripts = {"/db/data.sql"})
    void getUserByUserName(){
        User user = userService.findUserById(200L);
        assertThat(user).isNotNull();
    }
}
