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

import com.example.demo.dto.request.EmployeeApplicantRequest;
import com.example.demo.dto.request.LogInRequest;
import com.example.demo.dto.response.EmployeeApplicantLoginResponse;
import com.example.demo.model.EmployeeApplicant;
import com.example.demo.service.EmployeeApplicantService;

@Controller
public class EmployeeApplicantController {

	@Autowired
	private EmployeeApplicantService employeeApplicantService;
	
	@PostMapping("/apply/employee")
	public ResponseEntity<String> newEmployeeApplicant(@RequestBody EmployeeApplicantRequest employeeApplicantRequest) throws MessagingException {
		return new ResponseEntity<String>(employeeApplicantService.newEmployeeApplicant(employeeApplicantRequest), HttpStatus.CREATED);
	}
	
	@PostMapping("/login/employee/applicant")
	public ResponseEntity<EmployeeApplicantLoginResponse> employeeApplicantLogin(@RequestBody LogInRequest logInRequest) throws Exception{
		return new ResponseEntity<EmployeeApplicantLoginResponse>(employeeApplicantService.employeeApplicantLogin(logInRequest), HttpStatus.FOUND);
	}
	
	@GetMapping("/employee-applicant-detail")
	@PreAuthorize("hasRole('APPLICANT')")
	public ResponseEntity<EmployeeApplicant> employeeApplicantDetail(Principal principal){
		return new ResponseEntity<EmployeeApplicant>(employeeApplicantService.employeeApplicantDetail(principal), HttpStatus.OK);
	}
}
