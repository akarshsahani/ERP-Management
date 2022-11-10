package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.response.StudentAttendanceResponse;
import com.example.demo.model.Marks;
import com.example.demo.model.StudentAttendance;
import com.example.demo.repository.MarksRepository;
import com.example.demo.repository.StudentAttendanceRepository;
import com.example.demo.util.Util;

@Service
public class StudentAttendanceService {
	
	@Autowired
	private StudentAttendanceRepository studentAttendanceRepository;
	
	@Autowired
	private MarksRepository marksRepository;
	
	@Autowired
	private Util util;
	
	@Transactional
	public List<Marks> listOfStudentsRegisteredUnderSpecificTeacherAndCourse(String slot, String courseCode, Long teacherId){
		return marksRepository.listOfStudentsRegisteredUnderSpecificTeacherAndCourse(teacherId, courseCode, slot);
	}
	
	public StudentAttendanceResponse createAttendanceListOfStudentsEnrolledInSpecificCourseUnderSpecificTeacher(Long teacherId, String courseCode, String slot, String category, String attandanceDate, String classTime) {
		StudentAttendance studentAttendance = new StudentAttendance(); 
		List<Marks> listOfStudents = marksRepository.listOfStudentsRegisteredUnderSpecificTeacherAndCourse(teacherId, courseCode, slot);
		if(listOfStudents != null) {
			for (Marks student : listOfStudents) {
				studentAttendance = StudentAttendance.builder()
													 .registrationNumber(student.getRegistrationNumber())
													 .teacherId(teacherId)
													 .courseCode(courseCode)
													 .slot(slot)
													 .createdDate(LocalDate.now().toString())
													 .attendanceDate(attandanceDate)
													 .classTime(classTime)
													 .build();
				studentAttendanceRepository.save(studentAttendance);
			}
			return new StudentAttendanceResponse(studentAttendanceRepository.listOfStudentsAttendanceRegisteredUnderSpecificTeacherAndCourse(teacherId, courseCode, slot, attandanceDate), "Created Successfully");
		}
		else {
			return new StudentAttendanceResponse(null, "Something went wrong");
		}
	}
	
	public List<StudentAttendance> listOfStudentsAttendanceRegisteredUnderSpecificTeacherAndCourse(Long teacherId, String courseCode, String slot, String category, String attandanceDate){
		return studentAttendanceRepository.listOfStudentsAttendanceRegisteredUnderSpecificTeacherAndCourse(teacherId, courseCode, slot, attandanceDate);
	}

	public StudentAttendanceResponse updateAttendance(List<StudentAttendance> listOfStudentsRegisteredUnderSpecificTeacherAndCourse, Long teacherId, String courseCode, String slot, String category, String attandanceDate) {
		int i = 0;
		if(category.equalsIgnoreCase("employee")) {
			for (StudentAttendance studentAttendance : listOfStudentsRegisteredUnderSpecificTeacherAndCourse) {
				studentAttendanceRepository.updateAttendance(studentAttendance.getStatus(), studentAttendance.getAttendanceId());
			}
			return new StudentAttendanceResponse(studentAttendanceRepository.listOfStudentsAttendanceRegisteredUnderSpecificTeacherAndCourse(teacherId, courseCode, slot, attandanceDate), "Created Successfully");
		}
		else if(category.equalsIgnoreCase("teacher")){
			for (StudentAttendance studentAttendance : listOfStudentsRegisteredUnderSpecificTeacherAndCourse) {
				if(util.getNumberOfDaysBetweenCreatedDateAndCurrentDate(studentAttendance.getCreatedDate()) <= 1) {
					studentAttendanceRepository.updateAttendance(studentAttendance.getStatus(), studentAttendance.getAttendanceId());
					i++;
				}
				else {
					break;
				}
			}
			if(i>0) {
				return new StudentAttendanceResponse(studentAttendanceRepository.listOfStudentsAttendanceRegisteredUnderSpecificTeacherAndCourse(teacherId, courseCode, slot, attandanceDate), "Updated Successfully");
			}
			else {
				return new StudentAttendanceResponse(null, "Unable to update. Unauthorised");
			}
			
		}
		else {
			return new StudentAttendanceResponse(null, "Something went wrong");
		}
	}
}
