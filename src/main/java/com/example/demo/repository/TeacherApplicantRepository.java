package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.TeacherApplicant;

@Repository
public interface TeacherApplicantRepository extends JpaRepository<TeacherApplicant, Long> {

	TeacherApplicant findByPersonalEmail(String username);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE teacher_applicant SET current_status = ?1 WHERE teacher_applicant_id = ?2 ",  nativeQuery = true)
	public void updateStatuOfTeacherApplicant(String currentStatus, Long id);

	TeacherApplicant findByTeacherApplicantId(Long id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE teacher_applicant SET password = ?1 WHERE teacher_applicant_id = ?2 ",  nativeQuery = true)
	public void updatePassword(String password, Long id);

}
