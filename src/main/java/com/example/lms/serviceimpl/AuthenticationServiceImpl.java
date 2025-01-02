package com.example.lms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.lms.dtos.LoginUserDto;
import com.example.lms.dtos.RegisterUserDto;
import com.example.lms.jwt.AuthenticationService;
import com.example.lms.model.UserInfo;
import com.example.lms.repository.UserRepository;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private PasswordEncoder passEncoder;

    @Autowired
    private EmailServiceImpl emailService;

    @Override
    public String signup(RegisterUserDto input) {
        // Check if the user already exists
        if (userRepo.findByFullName(input.getFullName()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        // Create and save the new user
        UserInfo user = new UserInfo();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setRole(input.getRole());
        user.setPassword(passEncoder.encode(input.getPassword()));
        userRepo.save(user);

        // Send email dynamically after successful registration
        String subject = "Welcome to Our Platform!";
        String text = "Dear " + input.getFullName() + ",\n\nThank you for registering. We are excited to have you on board!\n\nBest Regards,\nYour Company";

        // Assuming you want to send an email from your own SMTP configuration, pass those details here
        emailService.sendEmail(
                "smtp.gmail.com",  // SMTP host
                "587",              // SMTP port
                "rohithparimella724@gmail.com", // Your email
                "wtsr wnev cwhs hzkq",  // Your email password (or app password)  Note : This password varies from PC's
                input.getEmail(),       // Recipient's email
                subject,                // Subject of the email
                text                    // Email body
        );


        if ("ROLE_ADMIN".equalsIgnoreCase(input.getRole())) {
            return "Admin registered successfully";
        } else {
            return "User registered successfully";
        }
    }

    @Override
    public UserInfo authenticate(LoginUserDto input) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getFullName(),
                        input.getPassword()
                )
        );

        return userRepo.findByFullName(input.getFullName())
                .orElseThrow();
    }

    public List<UserInfo> allUsers() {
        return userRepo.findAll();
    }
}
