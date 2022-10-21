package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.StudentApplicant;

@Repository
public interface StudentApplicantRepository extends JpaRepository<StudentApplicant, Long> {

	StudentApplicant findByEmail(String email);

}
