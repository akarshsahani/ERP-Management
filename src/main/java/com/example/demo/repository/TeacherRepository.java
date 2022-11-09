package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	Teacher findByOfficialEmail(String email);
	
	Teacher findByPersonalEmail(String email);
	
	Teacher findByTeacherId(Long teacherId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE teacher SET password = ?1 WHERE teacher_id = ?2 ",  nativeQuery = true)
	public void updatePassword(String password, Long id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE teacher SET official_email = ?1 WHERE teacher_id = ?2 ",  nativeQuery = true)
	public void updateOfficialEmail(String officialEmail, Long id);
	
	@Query(value = "SELECT teacher_id FROM teacher WHERE personal_email = ?1", nativeQuery = true)
	public Long returnTeacherId(String email);
	
	@Query(value = "SELECT first_name FROM course WHERE teacher_id = ?1", nativeQuery = true)
	public String returnTeacherFirstName(Long teacherId);

}
