package com.example.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lms.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

//	Optional<Student> findByName(String name);
}