package com.example.lms.dtos;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequestDto {
	private int id;
	@NotEmpty(message = "Name cannot be empty")
	private String name;

	@NotNull(message = "Age is required")
	private int age;

	private String mobno;

	@Email(message = "Email should be valid")
	@NotEmpty(message = "Email is required")
	private String email;

	private List<BookRequestDto> books; // List of books

}
