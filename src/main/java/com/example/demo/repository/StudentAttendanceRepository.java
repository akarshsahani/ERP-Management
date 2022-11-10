package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.StudentAttendance;

public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance, Long> {
	
//	@Transactional
//	@Query(value = "SELECT * FROM marks WHERE teacher_id = ?1 AND course_code = ?2 AND slot = ?3 AND attendance_date = ?4",  nativeQuery = true)
//	public List<StudentAttendance> listOfStudentsRegisteredUnderSpecificTeacherAndCourse(Long teacherId, String courseCode, String slot, String attendanceDate);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE student_attendance SET created_date = ?1, attendance_date = ?2, registration_number =?3, WHERE teacher_id = ?3 AND course_code = ?4 AND slot = ?5 AND registration_number = ?6",  nativeQuery = true)
	public void createAttendanceSheetForSpecificCourseOfSpecificTeacher(String createdDate, String attendanceDate, Long teacherId, String courseCode, String slot, String registrationNumber);

	@Modifying
	@Transactional
	@Query(value = "UPDATE student_attendance SET status = ?1 WHERE attendance_id = ?2 ",  nativeQuery = true)
	public void updateAttendance(String status, Long attendanceId);
	
	@Transactional
	@Query(value = "SELECT * FROM student_attendance WHERE teacher_id = ?1 AND course_code = ?2 AND slot = ?3 AND attendance_date = ?4",  nativeQuery = true)
	public List<StudentAttendance> listOfStudentsAttendanceRegisteredUnderSpecificTeacherAndCourse(Long teacherId, String courseCode, String slot, String attendanceDate);
}
