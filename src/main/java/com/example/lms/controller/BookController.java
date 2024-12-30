package com.example.lms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.dtos.BookRequestDto;
import com.example.lms.dtos.BookResponseDto;
import com.example.lms.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;
    private static final Logger logger=LoggerFactory.getLogger(BookController.class);


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public BookResponseDto addBook(@RequestBody BookRequestDto  bookRequestDto){
    	logger.info("Book is added successfully");
         return  bookService.addBook(bookRequestDto);
    }
}
