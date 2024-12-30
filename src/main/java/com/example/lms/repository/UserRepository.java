package com.example.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lms.model.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer> {

	Optional<UserInfo> findByFullName(String fullName);
}
