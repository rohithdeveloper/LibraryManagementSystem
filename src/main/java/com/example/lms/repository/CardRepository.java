package com.example.lms.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lms.model.LibraryCard;

@Repository
public interface CardRepository extends JpaRepository<LibraryCard,UUID> {
}