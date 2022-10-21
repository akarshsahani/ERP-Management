package com.example.demo.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.StudentApplicantRequest;
import com.example.demo.model.StudentApplicant;
import com.example.demo.repository.StudentApplicantRepository;
import com.example.demo.util.Util;

@Service
public class StudentApplicantServices {
	
	@Autowired
	private StudentApplicantRepository studentApplicantRepository;
	
	@Autowired
	private Util util;
	
	
	StudentApplicant studentApplicant = new StudentApplicant();
	
	public String newStudentApplicant(StudentApplicantRequest studentApplicantRequest) {
		
		studentApplicant = StudentApplicant.builder().firstName(studentApplicantRequest.getFirstName())
														.middleName(studentApplicantRequest.getMiddleName())
														.lastName(studentApplicantRequest.getLastName())
														.email(studentApplicantRequest.getEmail())
														.password(studentApplicantRequest.getPassword())
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
														.currentStatus("Applicant")
														.appliedDate(LocalDate.now())
														.build();
		
		studentApplicantRepository.save(studentApplicant);
		
		if(Integer.parseInt(studentApplicantRequest.getTenthPercentage())<65)
			return "Requirement Unsatisfied: Your percebtage is below 65%.";
		
		else if(Integer.parseInt(studentApplicantRequest.getTwelvethPercentage())<65)
			return "Requirement Unsatisfied: Your percebtage is below 65%.";
		
		else
			return "Application Submitted.";
	}

}
