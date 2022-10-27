package com.example.demo.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.model.Student;
import com.example.demo.model.StudentApplicant;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.StudentApplicantRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.util.Util;



@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired 
	private StudentApplicantRepository studentApplicantRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private Util util;
	
	Student student = new Student();
	
	public List<StudentApplicant> allStudentApplicant(){
		return studentApplicantRepository.findAll();
	}
	
	public String approveStudentApplicant(Long id) {
		
		Role role = roleRepository.findByRoleName("STUDENT");
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		System.out.println("------------" +role);
		studentApplicantRepository.updateStatusAndRoleOfStudentApplicant("STUDENT", id);
		System.out.println("---------- update 1");
		StudentApplicant applicant = studentApplicantRepository.findByStudentApplicantId((long) id);
		System.out.println("----------- update 2");
		student = Student.builder()
						 .firstName(applicant.getFirstName())
						 .middleName(applicant.getMiddleName())
						 .lastName(applicant.getLastName())
						 .email(applicant.getEmail())
//						 .password(applicant.getPassword())
						 .dateOfBirth(applicant.getDateOfBirth())
						 .phoneNumber(applicant.getPhoneNumber())
						 .permanentAddress(applicant.getPermanentAddress())
						 .currentAddress(applicant.getCurrentAddress())
						 .tenthPercentage(applicant.getTenthPercentage())
						 .tenthSchoolName(applicant.getTenthSchoolName())
						 .tenthSchoolAddress(applicant.getTenthSchoolAddress())
						 .tenthYearPassed(applicant.getTenthYearPassed())
						 .twelvethPercentage(applicant.getTwelvethPercentage())
						 .twelvethSchoolName(applicant.getTwelvethSchoolName())
						 .twelvethSchoolAddress(applicant.getTwelvethSchoolAddress())
						 .twelvethYearPassed(applicant.getTwelvethYearPassed())
						 .branch(applicant.getBatch())
						 .programme(applicant.getProgramme())
						 .batch(applicant.getBatch())
						 .currentStatus(applicant.getCurrentStatus())
						 .currentSemester("1")
						 .currentYear("1")
						 .admissionDate(LocalDate.now())
						 .appliedDate(applicant.getAppliedDate())
						 .role(roles)
						 .build();
		System.out.println("--------- build complete");
		System.out.println(student);
		studentRepository.save(student);
		System.out.println("------- student saved");
		studentRepository.updateRegistrationNumber(util.generateRegistrationNumber(studentRepository.returnStudentId(applicant.getEmail()), applicant.getProgramme()), applicant.getEmail());
		
		return "Application approved. Now you can login to student login. Please use registered email to generate password";
	}
}
