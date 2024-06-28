package com.mavericks.mavericksHub.services.implementations;

import com.mavericks.mavericksHub.dtos.request.CreateUserRequest;
import com.mavericks.mavericksHub.dtos.responses.CreateUserResponse;
import com.mavericks.mavericksHub.exception.UserNotExistException;
import com.mavericks.mavericksHub.models.User;
import com.mavericks.mavericksHub.repositories.UserRepository;
import com.mavericks.mavericksHub.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MavericksHubUserService implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public CreateUserResponse register(CreateUserRequest request){
        User user = mapper.map(request,User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        var response = mapper.map(user,CreateUserResponse.class);
        response.setMessage("success");
        return response;
    }
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotExistException(String.format("user with %d does not exist",id)));

    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(()->new UserNotExistException("user not found"));
    }


}
