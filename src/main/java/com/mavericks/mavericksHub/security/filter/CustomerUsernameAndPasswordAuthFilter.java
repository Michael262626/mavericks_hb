package com.mavericks.mavericksHub.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavericks.mavericksHub.dtos.request.LoginRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.http.impl.bootstrap.HttpServer;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class CustomerUsernameAndPasswordAuthFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private final ObjectMapper mapper = new ObjectMapper();
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response){
        try{
            //1. retrieve auth credentials from request bosy
            InputStream requestBodyStream = request.getInputStream();
            //2.convert json data from 1. to  java object
            LoginRequest loginRequest = mapper.readValue(requestBodyStream, LoginRequest.class);
            //3. create authentication object  dat is not yet auth.ted
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(), loginRequest.getPassword());
            //4. Pass the uauthenticated Authentication object to d uthenticationManager
            //4b. get back authentication result from authentication manager
            Authentication authenticationResult = authenticationManager.authenticate(authentication);
            //put the authentication result in the security context
            SecurityContextHolder.getContext().setAuthentication(authenticationResult);
            return authenticationResult;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public CustomerUsernameAndPasswordAuthFilter() {
        super();
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        super.successfulAuthentication(request, response, chain, authResult);
        String token = JWT.create()
            .withIssuer("mavericks_hub")
            .withArrayClaim("roles",getClaimsFrom(authResult.getAuthorities()))
//            .withExpiresAt(new Date(LocalDate.now().plusDays(3).toEpochDay()))
            .withExpiresAt(Instant.now().plusSeconds(24*60*60))//valid for 24 hours
            .sign(Algorithm.HMAC512("secret"));
        Map<String, String> res = new HashMap<>();
        res.put("access_token", token);
        response.getOutputStream().write(mapper.writeValueAsBytes(res));
        response.flushBuffer();
        chain.doFilter(request,response);
    }

    private static String[] getClaimsFrom(Collection<? extends GrantedAuthority>grantedAuthorities){
        return grantedAuthorities.stream()
                .map((grantedAuthority)->grantedAuthority.getAuthority())
                .toArray(String[]::new);
    }
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
