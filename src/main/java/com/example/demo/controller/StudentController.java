package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.request.LogInRequest;
import com.example.demo.dto.response.StudentLoginResponse;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/login/student")
	public ResponseEntity<StudentLoginResponse> loginStudent(@RequestBody LogInRequest logInRequest) throws Exception{
		return new ResponseEntity<StudentLoginResponse>(studentService.studentLogin(logInRequest), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("student/detail")
	@PreAuthorize("hasRole('STUDENT')")
	public ResponseEntity<Student> studentDetail(Principal principal){
		return new ResponseEntity<Student>(studentService.studentDetail(principal), HttpStatus.FOUND);
	}

}
