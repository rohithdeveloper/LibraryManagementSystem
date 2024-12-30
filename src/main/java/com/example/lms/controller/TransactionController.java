package com.example.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.dtos.IssueBookRequestDto;
import com.example.lms.dtos.IssueBookResponseDto;
import com.example.lms.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/issuebook")
    public IssueBookResponseDto  issueBook(@RequestBody IssueBookRequestDto  issueBookRequestDto) {
    	
    return 	transactionService.issueBook(issueBookRequestDto);
    }
 
}

