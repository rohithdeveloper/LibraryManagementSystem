package com.example.lms.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponseDto {
	private int id;
	private String name;

	private String email;
	// private List<BookRequestDto> books;
	private List<BookResponseDto> books; // List of books
}
