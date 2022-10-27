package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.StudentApplicant;
import com.example.demo.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/list-of-student-applicants")
	public ResponseEntity<List<StudentApplicant>> listOfStudentApplicant(){
		return new ResponseEntity<List<StudentApplicant>>(employeeService.allStudentApplicant(), HttpStatus.FOUND);
	}
	
	@PostMapping("/approve-student-applicant")
	public ResponseEntity<String> approveStudentApplicant(@RequestParam(value ="id") Long id){
		System.out.println("--------- " + id);
		return new ResponseEntity<String>(employeeService.approveStudentApplicant(id), HttpStatus.OK);
	}
	
	
}
