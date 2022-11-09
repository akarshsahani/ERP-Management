package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	@Query(value = "SELECT course_name FROM course WHERE course_code = ?1", nativeQuery = true)
	public String returnCourseName(String courseCode);
}
