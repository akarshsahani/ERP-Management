package com.example.demo.controller;

import java.security.Principal;

import javax.mail.MessagingException;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.request.EmployeeApplicantRequest;
import com.example.demo.dto.request.LogInRequest;
import com.example.demo.dto.response.EmployeeApplicantLoginResponse;
import com.example.demo.model.EmployeeApplicant;
import com.example.demo.service.EmployeeApplicantService;

@Controller
@Validated
public class EmployeeApplicantController {

	@Autowired
	private EmployeeApplicantService employeeApplicantService;
	
	@PostMapping("/apply/employee")
	public ResponseEntity<String> newEmployeeApplicant(@RequestBody @Valid EmployeeApplicantRequest employeeApplicantRequest) throws MessagingException {
		return new ResponseEntity<String>(employeeApplicantService.newEmployeeApplicant(employeeApplicantRequest), HttpStatus.CREATED);
	}
	
	@GetMapping("/login/employee/applicant")
	public ResponseEntity<EmployeeApplicantLoginResponse> employeeApplicantLogin(@RequestBody @Valid LogInRequest logInRequest) throws Exception{
		return new ResponseEntity<EmployeeApplicantLoginResponse>(employeeApplicantService.employeeApplicantLogin(logInRequest), HttpStatus.FOUND);
	}
	
	@GetMapping("/employee-applicant-detail")
	@PreAuthorize("hasRole('APPLICANT')")
	public ResponseEntity<EmployeeApplicant> employeeApplicantDetail(@NotNull @NotBlank @NotEmpty Principal principal){
		return new ResponseEntity<EmployeeApplicant>(employeeApplicantService.employeeApplicantDetail(principal), HttpStatus.OK);
	}
}
