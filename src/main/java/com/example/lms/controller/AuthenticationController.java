
package com.example.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.dtos.LoginUserDto;
import com.example.lms.dtos.RegisterUserDto;
import com.example.lms.jwt.AuthenticationService;
import com.example.lms.jwt.JwtService;
import com.example.lms.model.LoginResponse;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody RegisterUserDto registerUserDto) {
        // Encode the password before saving the user
    	String registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping(value="/login", consumes = "application/json")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto loginUserDto) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginUserDto.getFullName(), loginUserDto.getPassword())
        );

        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(loginUserDto.getFullName());
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            loginResponse.setExpiresIn(jwtService.getExpirationTime());
            return ResponseEntity.ok(loginResponse);
        } else {
            throw new UsernameNotFoundException("Invalid User");
        }
    }

    
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
//        UserInfo authenticatedUser = authenticationService.authenticate(loginUserDto);
//
//        String jwtToken = jwtService.generateToken(authenticatedUser.getFullName());
//
//        LoginResponse loginResponse = new LoginResponse();
//        loginResponse.setToken(jwtToken);
//      loginResponse.setExpiresIn(jwtService.getExpirationTime());
//
//        return ResponseEntity.ok(loginResponse);
//    }
}
    
