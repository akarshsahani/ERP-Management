package com.example.demo.controller;

import java.security.Principal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.request.LogInRequest;
import com.example.demo.dto.response.StudentLoginResponse;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@Controller
@Validated
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/login/student")
	public ResponseEntity<StudentLoginResponse> loginStudent(@RequestBody @Valid LogInRequest logInRequest) throws Exception{
		return new ResponseEntity<StudentLoginResponse>(studentService.studentLogin(logInRequest), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("student/detail")
	@PreAuthorize("hasRole('STUDENT')")
	public ResponseEntity<Student> studentDetail(@NotBlank @NotNull @NotEmpty Principal principal){
		return new ResponseEntity<Student>(studentService.studentDetail(principal), HttpStatus.FOUND);
	}
	
	@GetMapping("/student/enroll/")
	@PreAuthorize("hasRole('STUDENT')")
	public ResponseEntity<String> enrollCourse(@RequestParam @NotBlank @NotNull @NotEmpty String courseCode, 
											   @RequestParam @NotNull Long teacherId, 
											   @RequestParam @NotBlank @NotNull @NotEmpty String slot, 
											   Principal principal){
		return new ResponseEntity<String>(studentService.enrollCourse(courseCode, teacherId, slot, principal), HttpStatus.OK);
	}
	
	@GetMapping("/student/unenroll/")
	@PreAuthorize("hasRole('STUDENT')")
	public ResponseEntity<String> unenrollStudent(@RequestParam @NotBlank @NotNull @NotEmpty String courseCode, 
												  @RequestParam @NotNull Long teacherId, 
												  @RequestParam @NotBlank @NotNull @NotEmpty String slot, 
												 Principal principal){
		return new ResponseEntity<String>(studentService.unenrollCourse(courseCode, teacherId, slot, principal), HttpStatus.OK);
	}

}
