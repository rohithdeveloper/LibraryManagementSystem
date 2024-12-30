package com.example.lms.dtos;

import com.example.lms.enums.Genre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {
	private int id;
	private String title;
    private int price;
    private Genre genre;

}
