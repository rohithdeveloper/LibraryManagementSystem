package com.example.lms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.dtos.AuthorRequestDto;
import com.example.lms.dtos.AuthorResponseDto;
import com.example.lms.service.AuthorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;
    
    private static final Logger logger=LoggerFactory.getLogger(AuthorController.class);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public AuthorResponseDto addAuthor(@Valid @RequestBody AuthorRequestDto author){
    	logger.info("Author is added successfully");
        return authorService.addAuthor(author);
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/authors")
    public List<AuthorResponseDto> getAuthor(){
        return authorService.getAuthor();
    }
}