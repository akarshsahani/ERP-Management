package com.example.demo.service;

import java.util.Date;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeApplicant;
import com.example.demo.model.Student;
import com.example.demo.model.StudentApplicant;
import com.example.demo.model.Teacher;
import com.example.demo.model.TeacherApplicant;
import com.example.demo.repository.EmployeeApplicantRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.StudentApplicantRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherApplicantRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.util.JwtUtil;
import com.example.demo.util.Util;

@Service
public class MainService {

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
	private Util util;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;
	

	public String resetPassword(String email) throws MessagingException {
		
		if(studentApplicantRepository.findByPersonalEmail(email) != null) {
			StudentApplicant studentApplicant = studentApplicantRepository.findByPersonalEmail(email);
			if(studentApplicant.getCurrentStatus().equalsIgnoreCase("applicant") == true) {
				util.sendMailWithOutAttachment(email, util.generatePasswordResetLink(email, studentApplicant.getCurrentStatus(), studentApplicant.getCategory()), "Password Reset Link");
				return "Password reset link sent to your email.";
			}else {
				Student student = studentRepository.findByOfficialEmail(email);
				util.sendMailWithOutAttachment(email, util.generatePasswordResetLink(email, student.getCurrentStatus(), student.getCategory()), "Password Reset Link");
				return "Password reset link sent to your email.";
			}
		}
		else if(teacherApplicantRepository.findByPersonalEmail(email) != null) {
			TeacherApplicant teacherApplicant = teacherApplicantRepository.findByPersonalEmail(email);
			if(teacherApplicant.getCurrentStatus().equalsIgnoreCase("teacher") == true) {
				util.sendMailWithOutAttachment(email, util.generatePasswordResetLink(email, teacherApplicant.getCurrentStatus(), teacherApplicant.getCategory()), "Password Reset Link");
				return "Password reset link sent to your email.";
			}else {
				Teacher teacher = teacherRepository.findByOfficialEmail(email);
				util.sendMailWithOutAttachment(email, util.generatePasswordResetLink(email, teacher.getCurrentStatus(), teacher.getCategory()), "Password Reset Link");
				return "Password reset link sent to your email.";
			}
		}
		else if(employeeApplicantRepository.findByPersonalEmail(email) != null) {
			EmployeeApplicant employeeApplicant = employeeApplicantRepository.findByPersonalEmail(email);
			if (employeeApplicant.getCurrentStatus().equalsIgnoreCase("employee") == true) {
				util.sendMailWithOutAttachment(email, util.generatePasswordResetLink(email, employeeApplicant.getCurrentStatus(), employeeApplicant.getCategory()), "Password Reset Link");
				return "Password reset link sent to your email.";
			}else {
				Employee employee = employeeRepository.findByOfficialEmail(email);
				util.sendMailWithOutAttachment(email, util.generatePasswordResetLink(email, employee.getCurrentStatus(), employee.getCategory()), "Password Reset Link");
				return "Password reset link sent to your email.";
			}
		}
		return "Please enter correct email";
	}
	
	public String resetPasswordUsingLink (String category, String currentStatus, Long id, String password, String confirmPassword, String token) throws MessagingException {
		
		if(jwtUtil.isTokenExpired(token) != true) {
			if(password.equals(confirmPassword)) {
				if(category.equalsIgnoreCase("student") && currentStatus.equalsIgnoreCase("student")) {
					studentRepository.updatePassword(passwordEncoder.encode(password), id);
					util.sendMailWithOutAttachment(jwtUtil.extractUsername(token), "Password changed successfully! " + new Date(), "Password Changed.");
					return "Password successfully changed for student.";
				}else if(category.equalsIgnoreCase("student") && currentStatus.equalsIgnoreCase("applicant")) {
					studentApplicantRepository.updatePassword(passwordEncoder.encode(password), id);
					util.sendMailWithOutAttachment(jwtUtil.extractUsername(token), "Password changed successfully! " + new Date(), "Password Changed.");
					return "Password successfully changed for student applicant.";
				}else if(category.equalsIgnoreCase("teacher") && currentStatus.equalsIgnoreCase("teacher")) {
					teacherRepository.updatePassword(passwordEncoder.encode(password), id);
					util.sendMailWithOutAttachment(jwtUtil.extractUsername(token), "Password changed successfully! " + new Date(), "Password Changed.");
					return "Password successfully changed for teacher.";
				}else if(category.equalsIgnoreCase("teacher") && currentStatus.equalsIgnoreCase("applicant")) {
					teacherApplicantRepository.updatePassword(passwordEncoder.encode(password), id);
					util.sendMailWithOutAttachment(jwtUtil.extractUsername(token), "Password changed successfully! " + new Date(), "Password Changed.");
					return "Password successfully changed for teacher applicant.";
				}else if(category.equalsIgnoreCase("employee") && currentStatus.equalsIgnoreCase("employee")) {
					employeeRepository.updatePassword(passwordEncoder.encode(password), id);
					util.sendMailWithOutAttachment(jwtUtil.extractUsername(token), "Password changed successfully! " + new Date(), "Password Changed.");
					return "Password successfully changed for employee.";
				}else if(category.equalsIgnoreCase("employee") && currentStatus.equalsIgnoreCase("applicant")) {
					employeeApplicantRepository.updatePassword(passwordEncoder.encode(password), id);
					util.sendMailWithOutAttachment(jwtUtil.extractUsername(token), "Password changed successfully! " + new Date(), "Password Changed.");
					return "Password successfully changed for employee applicant.";
				}else {
					return "Something went wrong. Please Try again!";
				}
			}else {
				return "Password and confirm password doen't match.";
			}
		}else {
			return "Link is expired.";
		}
	}
	

}
