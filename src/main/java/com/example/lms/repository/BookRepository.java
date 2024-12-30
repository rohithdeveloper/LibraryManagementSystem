package com.example.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lms.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}