package com.example.demo.service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.LogInRequest;
import com.example.demo.dto.response.EmployeeLoginResponse;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeApplicant;
import com.example.demo.model.Role;
import com.example.demo.model.Student;
import com.example.demo.model.StudentApplicant;
import com.example.demo.model.Teacher;
import com.example.demo.model.TeacherApplicant;
import com.example.demo.repository.EmployeeApplicantRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.StudentApplicantRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherApplicantRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.util.Util;



@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired 
	private EmployeeApplicantRepository employeeApplicantRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired 
	private StudentApplicantRepository studentApplicantRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private TeacherApplicantRepository teacherApplicantRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private Util util;
	
	@Autowired
	private JwtService jwtService;
	
	
	
	public EmployeeLoginResponse employeeLogin(LogInRequest logInRequest) throws Exception {
		String jwtToken = jwtService.createJwtToken(logInRequest);
		Employee employee = employeeRepository.findByEmail(logInRequest.getEmail());
		String message = "Login Successful!";
		return new EmployeeLoginResponse(employee, jwtToken, message);
	}
	
	public Employee employeeDetails(Principal principal) {
		return employeeRepository.findByEmail(principal.getName());
	}
	
	
	
	
	public List<StudentApplicant> allStudentApplicant(){
		return studentApplicantRepository.findAll();
	}
	
	public String approveStudentApplicant(Long id) throws MessagingException {
		Student student = new Student();
		Role role = roleRepository.findByRoleName("student");
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		studentApplicantRepository.updateStatuOfStudentApplicant("student", id);
		StudentApplicant applicant = studentApplicantRepository.findByStudentApplicantId((long) id);
		student = Student.builder()
						 .firstName(applicant.getFirstName())
						 .middleName(applicant.getMiddleName())
						 .lastName(applicant.getLastName())
						 .email(applicant.getEmail())
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
						 .category(applicant.getCategory())
						 .role(roles)
						 .build();
		
		studentRepository.save(student);
		studentRepository.updateRegistrationNumber(util.generateRegistrationNumber(studentRepository.returnStudentId(applicant.getEmail()), applicant.getProgramme()), applicant.getEmail());
		util.sendMailWithAttachment(applicant.getEmail(), "Your Application for admission got approved.", "Application approved", "C:/Users/Softsuave/Downloads/aa.png");
		util.sendMailWithAttachment("akash.sahani@softsuave.org", "Your Application for admission got approved.", "Application approved", "C:/Users/Softsuave/Downloads/aa.png");
		String passwordResetLink = util.generatePasswordResetLink(applicant.getEmail(), applicant.getCurrentStatus(), applicant.getCategory());
		if(passwordResetLink != null) {
			util.sendMailWithAttachment(applicant.getEmail(), passwordResetLink, "Reset Password", "C:/Users/Softsuave/Downloads/aa.png");
			util.sendMailWithAttachment("akash.sahani@softsuave.org", passwordResetLink, "Reset Password", "C:/Users/Softsuave/Downloads/aa.png");
		}
		return "Application approved. Now you can login to student login. Please use registered email to generate password";
	}

	public String approveEmployeeApplicant(Long id) throws MessagingException {
		
		employeeApplicantRepository.updateStatuOfEmployeeApplicant("employee", id);
		EmployeeApplicant applicant = employeeApplicantRepository.findByEmployeeApplicantId(id);
		
		Role role = roleRepository.findByRoleName(applicant.getDesignation().toUpperCase());
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		
		Employee employee = new Employee();
		employee = Employee.builder()
						   .firstName(applicant.getFirstName())
						   .middleName(applicant.getMiddleName())
						   .lastName(applicant.getLastName())
						   .email(applicant.getEmail())
						   .dateOfBirth(applicant.getDateOfBirth())
						   .phoneNumber(applicant.getPhoneNumber())
						   .permanentAddress(applicant.getPermanentAddress())
						   .currentAddress(applicant.getCurrentAddress())
						   .joiningDate(LocalDate.now())
						   .appliedDate(applicant.getAppliedDate())
						   .qualification(applicant.getQualification())
						   .category(applicant.getCategory())
						   .designation(applicant.getDesignation())
						   .currentStatus(applicant.getCurrentStatus())
						   .role(roles)
						   .build();
		
		employeeRepository.save(employee);
		util.sendMailWithAttachment(applicant.getEmail(), "Your Application for employment got approved.", "Application approved", "C:/Users/Softsuave/Downloads/aa.png");
		util.sendMailWithAttachment("akash.sahani@softsuave.org", "Your Application for employment got approved.", "Application approved", "C:/Users/Softsuave/Downloads/aa.png");
		String passwordResetLink = util.generatePasswordResetLink(applicant.getEmail(), applicant.getCurrentStatus(), applicant.getCategory());
		if(passwordResetLink != null) {
			util.sendMailWithAttachment(applicant.getEmail(), passwordResetLink, "Reset Password", "C:/Users/Softsuave/Downloads/aa.png");
			util.sendMailWithAttachment("akash.sahani@softsuave.org", passwordResetLink, "Reset Password", "C:/Users/Softsuave/Downloads/aa.png");
		}
		return "Application approved. Now you can login to employee login. Please use registered email to generate password";
	}
	
	public String approveTeacherApplicant (Long id) throws MessagingException {
		
		teacherApplicantRepository.updateStatuOfTeacherApplicant("teacher", id);
		TeacherApplicant applicant = teacherApplicantRepository.findByTeacherApplicantId(id);
		
		Role role = roleRepository.findByRoleName(applicant.getDesignation().toUpperCase());
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		
		Teacher teacher = new Teacher();
		teacher = Teacher.builder()
				 .firstName(applicant.getFirstName())
				 .middleName(applicant.getMiddleName())
				 .lastName(applicant.getLastName())
				 .email(applicant.getEmail())
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
				 .bachelorPercentage(applicant.getBachelorPercentage())
				 .bachelorSchoolName(applicant.getBachelorSchoolName())
				 .bachelorSchoolAddress(applicant.getBachelorSchoolAddress())
				 .bachelorBranch(applicant.getBachelorBranch())
				 .bachelorProgramme(applicant.getBachelorProgramme())
				 .bachelorYearPassed(applicant.getBachelorYearPassed())
				 .mastersPercentage(applicant.getMastersPercentage())
				 .mastersSchoolName(applicant.getMastersSchoolName())
				 .mastersSchoolAddress(applicant.getMastersSchoolAddress())
				 .mastersBranch(applicant.getMastersBranch())
				 .mastersProgramme(applicant.getMastersProgramme())
				 .mastersYearPassed(applicant.getMastersYearPassed())
				 .otherQualifications(applicant.getOtherQualifications())
				 .joiningDate(LocalDate.now())
				 .appliedDate(applicant.getAppliedDate())
				 .category(applicant.getCategory())
				 .designation(applicant.getDesignation())
				 .role(roles)
				 .currentStatus(applicant.getCurrentStatus())
				 .build();
		
		teacherRepository.save(teacher);
		util.sendMailWithAttachment(applicant.getEmail(), "Your Application for teacher got approved.", "Application approved", "C:/Users/Softsuave/Downloads/aa.png");
		util.sendMailWithAttachment("akash.sahani@softsuave.org", "Your Application for teacher got approved.", "Application approved", "C:/Users/Softsuave/Downloads/aa.png");
		String passwordResetLink = util.generatePasswordResetLink(applicant.getEmail(), applicant.getCurrentStatus(), applicant.getCategory());
		if(passwordResetLink != null) {
			util.sendMailWithAttachment(applicant.getEmail(), passwordResetLink, "Reset Password", "C:/Users/Softsuave/Downloads/aa.png");
			util.sendMailWithAttachment("akash.sahani@softsuave.org", passwordResetLink, "Reset Password", "C:/Users/Softsuave/Downloads/aa.png");
		}
		return "Application approved. Now you can login to student login. Please use registered email to generate password";
	}
}
