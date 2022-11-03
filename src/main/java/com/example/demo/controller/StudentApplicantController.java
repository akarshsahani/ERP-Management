package com.example.demo.controller;

import java.security.Principal;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.request.LogInRequest;
import com.example.demo.dto.request.StudentApplicantRequest;
import com.example.demo.dto.response.StudentApplicantLoginResponse;
import com.example.demo.model.StudentApplicant;
import com.example.demo.service.StudentApplicantServices;

@Controller
public class StudentApplicantController {
	
	@Autowired
	private StudentApplicantServices studentApplicantServices;
	
	@PostMapping("/apply/student")
	public ResponseEntity<String> newStudentApplicant(@RequestBody StudentApplicantRequest studentApplicantRequest) throws MessagingException {
		return new ResponseEntity<String>(studentApplicantServices.newStudentApplicant(studentApplicantRequest), HttpStatus.CREATED);
	}
	
	@PostMapping({"/login/student/applicant"})
	public ResponseEntity<StudentApplicantLoginResponse> studentApplicantLogin(@RequestBody LogInRequest logInRequest) throws Exception{
		return new ResponseEntity<StudentApplicantLoginResponse>(studentApplicantServices.studentApplicantLogin(logInRequest), HttpStatus.FOUND);
	}
	
	@GetMapping({"/student-applicant-detail"})
	@PreAuthorize("hasRole('EMPLOYEE')")
	public ResponseEntity<StudentApplicant> studentApplicantDetails(Principal principal){
		return new ResponseEntity<StudentApplicant>(studentApplicantServices.studentApplicantDetails(principal), HttpStatus.FOUND);
	}
	

}
