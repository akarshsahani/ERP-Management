package com.example.demo.service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.Education;
import com.example.demo.dto.request.EmployeeApplicantRequest;
import com.example.demo.dto.request.LogInRequest;
import com.example.demo.dto.response.EmployeeApplicantLoginResponse;
import com.example.demo.model.EmployeeApplicant;
import com.example.demo.model.Role;
import com.example.demo.repository.EmployeeApplicantRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.util.Util;

@Service
public class EmployeeApplicantService {

	@Autowired
	private EmployeeApplicantRepository employeeApplicantRepository;
	
	@Autowired
	private Util util;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	EmployeeApplicant employeeApplicant = new EmployeeApplicant();
	Education education = new Education();
	
	public String newEmployeeApplicant (EmployeeApplicantRequest employeeApplicantRequest) throws MessagingException {
		
		Role role = roleRepository.findByRoleName("APPLICANT");
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		
//		Education education = util.addNewEducationalQualificatin(employeeApplicantRequest.getQualification());
//		Set<Education> edu = new HashSet<>();
//		edu.add(education);
		
		
		employeeApplicant = EmployeeApplicant.builder()
											 .firstName(employeeApplicantRequest.getFirstName())
											 .middleName(employeeApplicantRequest.getMiddleName())
											 .lastName(employeeApplicantRequest.getLastName())
											 .personalEmail(employeeApplicantRequest.getEmail())
											 .password(passwordEncoder.encode(employeeApplicantRequest.getPassword()))
											 .dateOfBirth(util.stringToDate(employeeApplicantRequest.getDateOfBirth()))
											 .phoneNumber(employeeApplicantRequest.getPhoneNumber())
											 .permanentAddress(employeeApplicantRequest.getPermanentAddress())
											 .currentAddress(employeeApplicantRequest.getCurrentAddress())
											 .category("employee")
											 .currentStatus("Applicant")
											 .designation(employeeApplicantRequest.getDesignation())
//											 .qualification(employeeApplicantRequest.getQualification())
											 .appliedDate(LocalDate.now())
											 .role(roles)
											 .build();
		employeeApplicantRepository.save(employeeApplicant);
		util.sendMailWithOutAttachment(employeeApplicantRequest.getEmail(), "Application Submitted Successfully! You can login to check your status.", "Application Submitted");
		return "Application Submitted Successfully! You can login to check your status.";
	}
	
	
	
	public EmployeeApplicantLoginResponse employeeApplicantLogin(LogInRequest logInRequest) throws Exception {
		EmployeeApplicant employeeApplicant = employeeApplicantRepository.findByPersonalEmail(logInRequest.getEmail());
		if(employeeApplicant != null){
			String jwtToken = jwtService.createJwtToken(logInRequest);
			if(employeeApplicant.getCurrentStatus().equalsIgnoreCase("employee")) {
				String message = "Application approved. Please Login to employee portal.";
				return new EmployeeApplicantLoginResponse(employeeApplicant, jwtToken, message);
			}
			else {
				String message = "Application Submitted Successfully. Please wait until approved.";
				return new EmployeeApplicantLoginResponse(employeeApplicant, jwtToken, message);
			}
		}else {
			return new EmployeeApplicantLoginResponse(employeeApplicant, null, "Email not registered.");
		}
		
		
	}
	
	public EmployeeApplicant employeeApplicantDetail(Principal principal) {
		return employeeApplicantRepository.findByPersonalEmail(principal.getName());
	}
}
