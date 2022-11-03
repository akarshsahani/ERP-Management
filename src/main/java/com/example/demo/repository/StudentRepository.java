package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	Student findByOfficialEmail(String username);
	
	Student findByPersonalEmail(String username);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE student SET registration_number = ?1 WHERE personal_email = ?2 ",  nativeQuery = true)
	public void updateRegistrationNumber(String registrationNumber, String email);
	
	@Query(value = "SELECT student_id FROM student WHERE personal_email = ?1", nativeQuery = true)
	public Long returnStudentId(String email);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE student SET password = ?1 WHERE student_id = ?2 ",  nativeQuery = true)
	public void updatePassword(String password, Long id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE student SET official_email = ?1 WHERE student_id = ?2 ",  nativeQuery = true)
	public void updateOfficialEmail(String officialEmail, Long id);

}
