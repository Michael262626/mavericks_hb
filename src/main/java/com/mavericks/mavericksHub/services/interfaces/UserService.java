package com.mavericks.mavericksHub.services.interfaces;

import com.mavericks.mavericksHub.dtos.request.CreateUserRequest;
import com.mavericks.mavericksHub.dtos.responses.CreateUserResponse;
import com.mavericks.mavericksHub.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    CreateUserResponse register(CreateUserRequest request);
    void deleteAll();

    User findUserById(long id);
    User getUserByUsername(String username);
}
