package com.example.lms.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequestDto {
	private int id;
	private String name;
	private int age; // Ensure this field is included and correctly mapped
	private String mobno;
	private String email;
	private List<BookRequestDto> books; // List of books

}
