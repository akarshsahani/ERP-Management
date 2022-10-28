package com.example.demo.service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.LogInRequest;
import com.example.demo.dto.request.StudentApplicantRequest;
import com.example.demo.dto.response.StudentApplicantLoginResponse;
import com.example.demo.model.Role;
import com.example.demo.model.StudentApplicant;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.StudentApplicantRepository;
import com.example.demo.util.Util;

@Service
public class StudentApplicantServices {
	
	@Autowired
	private StudentApplicantRepository studentApplicantRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private Util util;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	StudentApplicant studentApplicant = new StudentApplicant();
	
	public String newStudentApplicant(StudentApplicantRequest studentApplicantRequest) {
		
		Role role = roleRepository.findByRoleName("APPLICANT");
//		System.out.println(role);
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		
		studentApplicant = StudentApplicant.builder().firstName(studentApplicantRequest.getFirstName())
														.middleName(studentApplicantRequest.getMiddleName())
														.lastName(studentApplicantRequest.getLastName())
														.email(studentApplicantRequest.getEmail())
														.password(passwordEncoder.encode(studentApplicantRequest.getPassword()))
														.dateOfBirth(util.stringToDate(studentApplicantRequest.getDateOfBirth()))
														.phoneNumber(studentApplicantRequest.getPhoneNumber())
														.permanentAddress(studentApplicantRequest.getPermanentAddress())
														.currentAddress(studentApplicantRequest.getCurrentAddress())
														.tenthPercentage(studentApplicantRequest.getTenthPercentage())
														.tenthSchoolName(studentApplicantRequest.getTenthSchoolName())
														.tenthSchoolAddress(studentApplicantRequest.getTenthSchoolAddress())
														.tenthYearPassed(studentApplicantRequest.getTenthYearPassed())
														.twelvethPercentage(studentApplicantRequest.getTwelvethPercentage())
														.twelvethSchoolName(studentApplicantRequest.getTwelvethSchoolName())
														.twelvethSchoolAddress(studentApplicantRequest.getTwelvethSchoolAddress())
														.twelvethYearPassed(studentApplicantRequest.getTwelvethYearPassed())
														.branch(studentApplicantRequest.getBranch())
														.programme(studentApplicantRequest.getProgramme())
														.batch(studentApplicantRequest.getBatch())
														.currentStatus("applicant")
														.category("student")
														.role(roles)
														.appliedDate(LocalDate.now())
														.build();
		
		studentApplicantRepository.save(studentApplicant);
		
		if(Integer.parseInt(studentApplicantRequest.getTenthPercentage())<65)
			return "Requirement Unsatisfied: Your percebtage is below 65%.";
		
		else if(Integer.parseInt(studentApplicantRequest.getTwelvethPercentage())<65)
			return "Requirement Unsatisfied: Your percebtage is below 65%.";
		
		else
			return "Application Submitted. Please Login to view your status.";
	}
	
	public StudentApplicantLoginResponse studentApplicantLogin(LogInRequest logInRequest) throws Exception {
		String jwtToken = jwtService.createJwtToken(logInRequest);
		StudentApplicant studentApplicant = studentApplicantRepository.findByEmail(logInRequest.getEmail());
		
		if(studentApplicant.getCurrentStatus().equalsIgnoreCase("STUDENT")) {
			String message = "Application approved. Please Login to student portal.";
			return new StudentApplicantLoginResponse(studentApplicant, jwtToken, message);
		}
		else {
			String message = "Application Submitted Successfully. Please wait until approved.";
			return new StudentApplicantLoginResponse(studentApplicant, jwtToken, message);
		}
		
	}
	
	public StudentApplicant studentApplicantDetails(Principal principal) {
		return studentApplicantRepository.findByEmail(principal.getName());
	}
		
}
