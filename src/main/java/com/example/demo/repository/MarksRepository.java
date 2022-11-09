package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Marks;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Long> {

	@Query(value = "SELECT * FROM marks WHERE registration_number = ?1",  nativeQuery = true)
	public List<Marks> getListOfMarksByRegistrationNumber(String registrationNumber);
	
	@Query(value = "SELECT * FROM marks WHERE course_code = ?1",  nativeQuery = true)
	public List<Marks> getListOfMarksByCourseCode(String courseCode);
	
	@Query(value = "SELECT * FROM marks WHERE registration_number = ?1 AND course_code = ?2",  nativeQuery = true)
	public List<Marks> getListOfMarksByRegistrationNumberAndCourseCode(String registrationNumber, String courseCode);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE marks SET unit_test_one_marks_date = ?1 WHERE marks_id = ?2 ",  nativeQuery = true)
	public void updateUnitTestOneDate(String dateTime, String marksId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE marks SET mid_test_one_marks_date = ?1 WHERE marks_id = ?2 ",  nativeQuery = true)
	public void updateMidTestOneDate(String dateTime, String marksId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE marks SET unit_test_two_marks_date = ?1 WHERE marks_id = ?2 ",  nativeQuery = true)
	public void updateUnitTestTwoDate(String dateTime, String marksId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE marks SET mid_test_two_marks_date = ?1 WHERE marks_id = ?2 ",  nativeQuery = true)
	public void updateMidTestTwoDate(String dateTime, String marksId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE marks SET assignment_one_marks_date = ?1 WHERE marks_id = ?2 ",  nativeQuery = true)
	public void updateAssignmentOneMarksDate(String dateTime, String marksId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE marks SET assignment_two_marks_date = ?1 WHERE marks_id = ?2 ",  nativeQuery = true)
	public void updateAssignmentTwoMarksDate(String dateTime, String marksId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE marks SET end_semester_marks_date = ?1 WHERE marks_id = ?2 ",  nativeQuery = true)
	public void updateEndSemesterMarksDate(String dateTime, String marksId);

	public Marks findByMarksId(String string);

	@Transactional
	public void deleteByMarksId(String returnMarksId);
	
	@Transactional
	@Query(value = "SELECT * FROM marks WHERE teacher_id = ?1 AND course_code = ?2 AND slot = ?3",  nativeQuery = true)
	public List<Marks> listOfStudentsRegisteredUnderSpecificTeacherAndCourse(Long teacherId, String courseCode, String slot);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE marks SET unit_test_one = ?1 WHERE marks_id = ?2 ",  nativeQuery = true)
	public void updateUnitTestOneMarks(float marks, String marksId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE marks SET unit_test_two = ?1 WHERE marks_id = ?2 ",  nativeQuery = true)
	public void updateMidTestOneMarks(float marks, String marksId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE marks SET mid_test_one = ?1 WHERE marks_id = ?2 ",  nativeQuery = true)
	public void updateUnitTestTwoMarks(float marks, String marksId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE marks SET mid_test_two_date = ?1 WHERE marks_id = ?2 ",  nativeQuery = true)
	public void updateMidTestTwoMarks(float marks, String marksId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE marks SET assignment_one = ?1 WHERE marks_id = ?2 ",  nativeQuery = true)
	public void updateAssignmentOneMarks(float marks, String marksId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE marks SET assignment_two = ?1 WHERE marks_id = ?2 ",  nativeQuery = true)
	public void updateAssignmentTwoMarks(float marks, String marksId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE marks SET end_semester_marks = ?1 WHERE marks_id = ?2 ",  nativeQuery = true)
	public void updateEndSemesterMarks(float marks, String marksId);
}
