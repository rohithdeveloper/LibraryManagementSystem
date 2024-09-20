package com.example.lms.service;

import com.example.lms.dtos.BookRequestDto;
import com.example.lms.dtos.BookResponseDto;

public interface BookService {
	BookResponseDto addBook(BookRequestDto bookDto);
}
