package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.StudentApplicant;

@Repository
public interface StudentApplicantRepository extends JpaRepository<StudentApplicant, Long> {

	StudentApplicant findByPersonalEmail(String email);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE student_applicant SET current_status = ?1 WHERE student_applicant_id = ?2 ",  nativeQuery = true)
	public void updateStatuOfStudentApplicant(String currentStatus, Long id);

	StudentApplicant findByStudentApplicantId(Long id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE student_applicant SET password = ?1 WHERE student_applicant_id = ?2 ",  nativeQuery = true)
	public void updatePassword(String password, Long id);

}
