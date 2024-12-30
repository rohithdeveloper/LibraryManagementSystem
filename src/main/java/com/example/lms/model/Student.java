package com.example.lms.model;

import com.example.lms.enums.Department;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private int sId;

	@Column(name = "name")
	private String sname;

	@Column(name = "age")
	private int sage;

	@Enumerated(EnumType.STRING)
	@Column(name = "department")
	private Department department;

	@Column(name = "email")
	private String email;

	@OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
	private LibraryCard card;
}

