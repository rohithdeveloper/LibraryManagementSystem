package com.example.lms.service;

import java.util.List;

import com.example.lms.dtos.AuthorRequestDto;
import com.example.lms.dtos.AuthorResponseDto;

public interface AuthorService {

	AuthorResponseDto addAuthor(AuthorRequestDto authorDto);
	List<AuthorResponseDto> getAuthor();
}
