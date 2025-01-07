package com.example.lms.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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

		// Set card ID as UUID in response DTO (cardno is now UUID)
		studentResponseDto.setCardId(savedStudent.getCard().getCardNo()); // Get UUID from LibraryCard

		log.info("Student is added  with id - {}", savedStudent.getSId().toString());
		return studentResponseDto;
	}

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
		} else {
			throw new StudentNotfoundException("Student not found");
		}
	}

	@Override
	public List<StudentResponseDto> getAllStudents() {
		// Fetch all students from the repository
		List<Student> students = studentRepository.findAll();
		List<StudentResponseDto> studentResponseDtos = new ArrayList<>();

		// Iterate through each student and create a StudentResponseDto
		for (Student student : students) {
			StudentResponseDto studentResponseDto = new StudentResponseDto();
			studentResponseDto.setSId(student.getSId());
			studentResponseDto.setSname(student.getSname());
			studentResponseDto.setDepartment(student.getDepartment());
			studentResponseDto.setEmail(student.getEmail());
			studentResponseDto.setCardId(student.getCard().getCardNo());

			// Add the StudentResponseDto to the response list
			studentResponseDtos.add(studentResponseDto);
		}

		// Return the list of StudentResponseDto
		log.info("Here is the list of students: {}",studentResponseDtos);
		return studentResponseDtos;
	}

}
