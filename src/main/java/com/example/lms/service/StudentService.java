package com.example.lms.service;

import java.util.List;

import com.example.lms.dtos.StudentRequestDto;
import com.example.lms.dtos.StudentResponseDto;
import com.example.lms.dtos.StudentUpdateEmailRequestDto;

public interface StudentService {

	StudentResponseDto addStudent(StudentRequestDto studentRequestDto);

	void updateEmail(StudentUpdateEmailRequestDto studentUpdateEmailRequestDto);
	
	List<StudentResponseDto> getAllStudents();

}
