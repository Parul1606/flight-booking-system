package com.fbs.central_api.filter;

import com.fbs.central_api.service.UserService;
import com.fbs.central_api.utility.AuthUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class AuthFilter extends OncePerRequestFilter {

    UserService userService;
    AuthUtility authUtility;

    @Autowired
    public AuthFilter(UserService userService,
                      AuthUtility authUtility){
        this.userService = userService;
        this.authUtility = authUtility;
    }
    // If token is valid then we will be setting username password authenthication and calling the doFilter method
    // If token is invalid then without setting username password authenthication we are directly calling doFilter

    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            String token = bearerToken.substring(7);
            // We got the token now we need to validate that this token is a genuine token or not.
            boolean isValid = userService.validateToken(token);
            if(isValid == false){
                // I am not going to set any kind of authentication and i will return from here it self
                // before filtering if i am not setting any kind of authetication that
                // means i am rejecting the reuquest
                filterChain.doFilter(request, response);
                return;
            }
            String payload = authUtility.decryptJwtToken(token);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(payload, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response); // If you are not setting up username and password authenthication that means you are rejecting token
    }
}
