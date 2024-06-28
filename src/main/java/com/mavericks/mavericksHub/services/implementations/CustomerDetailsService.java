package com.mavericks.mavericksHub.services.implementations;

import com.mavericks.mavericksHub.exception.UserNotExistException;
import com.mavericks.mavericksHub.models.User;
import com.mavericks.mavericksHub.security.model.SecuredUser;
import com.mavericks.mavericksHub.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CustomerDetailsService implements UserDetailsService {

    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            System.out.println("reached this point");
            User user = userService.getUserByUsername(username);
            return new SecuredUser(user);
        }catch(UserNotExistException error){
            System.out.println("error: reached this point");
            throw new UsernameNotFoundException(error.getMessage());
        }
    }
}

