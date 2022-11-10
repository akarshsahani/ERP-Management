package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.response.StudentAttendanceResponse;
import com.example.demo.model.StudentAttendance;
import com.example.demo.service.StudentAttendanceService;

@Controller
public class StudentAttendanceController {

	@Autowired
	private StudentAttendanceService studentAttendanceService;
	
	@GetMapping("/teacher/create-attendance-sheet/")
	public ResponseEntity<StudentAttendanceResponse> createAttendanceSheet(@RequestParam Long teacherId, @RequestParam String courseCode, @RequestParam String slot, @RequestParam String category, @RequestParam String attandanceDate, @RequestParam String classTime ){
		return new ResponseEntity<StudentAttendanceResponse>(studentAttendanceService.createAttendanceListOfStudentsEnrolledInSpecificCourseUnderSpecificTeacher(teacherId, courseCode, slot, category, attandanceDate, classTime), HttpStatus.OK);
	}
	
	@PostMapping("/teacher/update-attendance/")
	public ResponseEntity<StudentAttendanceResponse> updateAttendance(@RequestBody List<StudentAttendance> listOfStudentsRegisteredUnderSpecificTeacherAndCourse, @RequestParam Long teacherId, @RequestParam String courseCode, @RequestParam String slot, @RequestParam String category, @RequestParam String attandanceDate){
		return new ResponseEntity<StudentAttendanceResponse>(studentAttendanceService.updateAttendance(listOfStudentsRegisteredUnderSpecificTeacherAndCourse, teacherId, courseCode, slot, category, attandanceDate), HttpStatus.OK);
	}
	
	@GetMapping("/teacher/attendance-on-specific-date/")
	public ResponseEntity<List<StudentAttendance>> attendanceOnSpecificDate(@RequestParam Long teacherId, @RequestParam String courseCode, @RequestParam String slot, @RequestParam String category, @RequestParam String attandanceDate){
		return new ResponseEntity<List<StudentAttendance>>(studentAttendanceService.listOfStudentsAttendanceRegisteredUnderSpecificTeacherAndCourse(teacherId, courseCode, slot, category, attandanceDate), HttpStatus.FOUND);
	}
}
