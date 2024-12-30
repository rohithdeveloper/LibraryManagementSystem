package com.example.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lms.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
