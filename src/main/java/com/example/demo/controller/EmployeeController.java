package com.example.demo.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.StudentApplicant;
import com.example.demo.service.EmployeeService;
//import com.example.demo.util.Util;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
//	@Autowired
//	private Util util;
	
	@GetMapping("/list-of-student-applicants")
	public ResponseEntity<List<StudentApplicant>> listOfStudentApplicant(){
		return new ResponseEntity<List<StudentApplicant>>(employeeService.allStudentApplicant(), HttpStatus.FOUND);
	}
	
	@PostMapping("/approve-student-applicant")
	public ResponseEntity<String> approveStudentApplicant(@RequestParam(value ="id") Long id) throws MessagingException{
		System.out.println("--------- " + id);
		return new ResponseEntity<String>(employeeService.approveStudentApplicant(id), HttpStatus.OK);
	}
	
	@PostMapping("/reset-password")
	public ResponseEntity<String> testEmail(@RequestParam Long id, @RequestParam String token, @RequestBody String password) throws MessagingException {
//		String body = "Hello, this is test body.";
//		String subject = "test";
//		String attachment = "C:/Users/Softsuave/Downloads/aa.png";
//		String toEmail = "shailesh.devaraj@softsuave.org";
//		return new ResponseEntity<String>(util.sendMailWithAttachment(toEmail, body, subject, attachment), HttpStatus.OK);
		
		return new ResponseEntity<String>(null);
	}
	
	
}
