package com.example.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lms.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
}
