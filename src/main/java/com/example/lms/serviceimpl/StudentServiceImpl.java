package com.example.lms.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.dtos.StudentRequestDto;
import com.example.lms.dtos.StudentResponseDto;
import com.example.lms.dtos.StudentUpdateEmailRequestDto;
import com.example.lms.enums.CardStatus;
import com.example.lms.exceptions.StudentNotfoundException;
import com.example.lms.model.LibraryCard;
import com.example.lms.model.Student;
import com.example.lms.repository.CardRepository;
import com.example.lms.repository.StudentRepository;
import com.example.lms.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CardRepository libraryCardRepository;

	 @Override
	    public StudentResponseDto addStudent(StudentRequestDto studentDto) {
	        // Create a new Student entity and set its properties from the DTO
	        Student student = new Student();
	        student.setSname(studentDto.getSname());
	        student.setSage(studentDto.getSage());
	        student.setDepartment(studentDto.getDepartment());
	        student.setEmail(studentDto.getEmail());

	        // Create a new LibraryCard entity and set its properties
	        LibraryCard libraryCard = new LibraryCard();
	        libraryCard.setStatus(CardStatus.ACTIVATED);
	        libraryCard.setStudent(student); // Link the card with the student
	        student.setCard(libraryCard); // Link the student with the card

	        // Save the student (and the associated library card) to the database
	        // Ensure libraryCard is saved after student to maintain FK constraints
	        Student savedStudent = studentRepository.save(student);

	        // Create a StudentDto to return as the response
	        StudentResponseDto studentResponseDto = new StudentResponseDto();
	        studentResponseDto.setSId(savedStudent.getSId());
	        studentResponseDto.setSname(savedStudent.getSname());
	        studentResponseDto.setDepartment(savedStudent.getDepartment());
	        studentResponseDto.setEmail(savedStudent.getEmail());
	        studentResponseDto.setCardId(savedStudent.getCard().getCardno()); // Set card ID in response DTO

	        return studentResponseDto;
	    }
	 

//	 @Override
//	 public StudentResponseDto updateEmail(StudentUpdateEmailRequestDto studentUpdateEmailRequestDto) {
//	     // Retrieve the student entity from the database using the provided student ID
//	     Optional<Student> optionalStudent = studentRepository.findById(studentUpdateEmailRequestDto.getSId());
//
//	     // Use Optional to handle the presence or absence of the student entity
//	     if (optionalStudent.isPresent()) {
//	         Student student = optionalStudent.get();
//
//	         // Update the student's email address with the new value from the DTO
//	         student.setEmail(studentUpdateEmailRequestDto.getEmail());
//
//	         // Save the updated student entity back to the database
//	         Student savedStudent = studentRepository.save(student);
//
//	         // Create a new StudentResponseDto to return the updated student information
//	         StudentResponseDto studentResponseDto = new StudentResponseDto();
//	         studentResponseDto.setSId(savedStudent.getSId());
//	         studentResponseDto.setSname(savedStudent.getSname());
//	         studentResponseDto.setDepartment(savedStudent.getDepartment());
//	         studentResponseDto.setEmail(savedStudent.getEmail());
//
//	         // Return the DTO with the updated student information
//	         return studentResponseDto;
//	     } else {
//	         // Handle the case where the student was not found
//	         throw new StudentNotfoundException("Student not found");
//	     }
//	 }

	 @Override
	 public void updateEmail(StudentUpdateEmailRequestDto studentUpdateEmailRequestDto) {
	     // Retrieve the student entity from the database using the provided student ID
	     Optional<Student> optionalStudent = studentRepository.findById(studentUpdateEmailRequestDto.getSId());

	     // Use Optional to handle the presence or absence of the student entity
	     if (optionalStudent.isPresent()) {
	         Student student = optionalStudent.get();

	         // Update the student's email address with the new value from the DTO
	         student.setEmail(studentUpdateEmailRequestDto.getEmail());
	         studentRepository.save(student);
	     }
	     else {
	    	 throw new StudentNotfoundException("Student not found");
	     }
	 }
	

	
}

