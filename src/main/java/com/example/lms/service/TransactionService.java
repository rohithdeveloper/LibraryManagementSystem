package com.example.lms.service;


import com.example.lms.dtos.IssueBookRequestDto;
import com.example.lms.dtos.IssueBookResponseDto;

public interface TransactionService {

	IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto);
	
  
}