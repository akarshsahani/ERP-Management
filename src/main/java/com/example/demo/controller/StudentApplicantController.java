package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.request.StudentApplicantRequest;
import com.example.demo.service.StudentApplicantServices;

@Controller
public class StudentApplicantController {
	
	@Autowired
	private StudentApplicantServices studentApplicantServices;
	
	@PostMapping("/new-applicant")
	public ResponseEntity<String> newStudentApplicant(@RequestBody StudentApplicantRequest studentApplicantRequest) {
		String response = studentApplicantServices.newStudentApplicant(studentApplicantRequest);
		return new ResponseEntity<String>(response, HttpStatus.CREATED);
	}

}
