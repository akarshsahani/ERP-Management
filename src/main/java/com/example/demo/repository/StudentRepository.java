package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	Student findByEmail(String username);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE student SET registration_number = ?1 WHERE email = ?2 ",  nativeQuery = true)
	public void updateRegistrationNumber(String registrationNumber, String email);
	
	@Query(value = "SELECT student_id FROM student WHERE email = ?1", nativeQuery = true)
	public Long returnStudentId(String email);

}
