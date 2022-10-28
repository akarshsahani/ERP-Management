package com.example.demo.util;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.example.demo.model.Employee;
import com.example.demo.model.Role;
import com.example.demo.model.Student;
import com.example.demo.model.StudentApplicant;
import com.example.demo.model.Teacher;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.StudentApplicantRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;

@Component
public class Util {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private StudentApplicantRepository studentApplicantRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private JwtUtil jwtUtil;
	

	public LocalDate stringToDate(String stringDate) {
		return LocalDate.parse(stringDate);
//		https://www.sololearn.com/Discuss/1858324/pls-help-me-write-a-java-program-that-will-accept-someone-date-of-birth-using-the-current-date-to
	}
	
	public String generateRegistrationNumber(Long id, String programme) {
//		String date = (String)LocalDate.now();
		String str = Long.toString(id);
//		String registrationNumber = null;
		int length = str.length();
		if(programme.equalsIgnoreCase("computer science and engineering")) {
			if(length==1) {
				return "18" + "UE" + "CS" + "000" + str;
			}else if(length == 2) {
				return "18" + "UE" + "CS" + "00" + str;
			}else if(length == 3) {
				return "18" + "UE" + "CS" + "0" + str;
			}else {
				return "18" + "UE" + "CS" + str;
			}
		}else if(programme.equalsIgnoreCase("electronics and communication engineering")) {
			if(length==1) {
				return "18" + "UE" + "EC" + "000" + str;
			}else if(length == 2) {
				return "18" + "UE" + "EC" + "00" + str;
			}else if(length == 3) {
				return "18" + "UE" + "EC" + "0" + str;
			}else {
				return "18" + "UE" + "EC" + str;
			}
		}else if(programme.equalsIgnoreCase("Electrical and Electronics Engineering")) {
			if(length==1) {
				return "18" + "UE" + "EE" + "000" + str;
			}else if(length == 2) {
				return "18" + "UE" + "EE" + "00" + str;
			}else if(length == 3) {
				return "18" + "UE" + "EE" + "0" + str;
			}else {
				return "18" + "UE" + "EE" + str;
			}
		}else if(programme.equalsIgnoreCase("Mechanical Engineering")) {
			if(length==1) {
				return "18" + "UE" + "ME" + "000" + str;
			}else if(length == 2) {
				return "18" + "UE" + "ME" + "00" + str;
			}else if(length == 3) {
				return "18" + "UE" + "ME" + "0" + str;
			}else {
				return "18" + "UE" + "ME" + str;
			}
		}else if(programme.equalsIgnoreCase("Civil Engineering")) {
			if(length==1) {
				return "18" + "UE" + "CE" + "000" + str;
			}else if(length == 2) {
				return "18" + "UE" + "CE" + "00" + str;
			}else if(length == 3) {
				return "18" + "UE" + "CE" + "0" + str;
			}else {
				return "18" + "UE" + "CE" + str;
			}
		}else if(programme.equalsIgnoreCase("Information technology")) {
			if(length==1) {
				return "18" + "UE" + "IT" + "000" + str;
			}else if(length == 2) {
				return "18" + "UE" + "IT" + "00" + str;
			}else if(length == 3) {
				return "18" + "UE" + "IT" + "0" + str;
			}else {
				return "18" + "UE" + "IT" + str;
			}
		}else {
			return "Cannot create registration number";
		}
	}
	
	
	public String sendMailWithAttachment(String toEmail, String body, String subject, String attachment) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		
		
		mimeMessageHelper.setFrom("akashsahani.ss703@gmail.com");
		mimeMessageHelper.setTo(toEmail);
		mimeMessageHelper.setText(body);
		mimeMessageHelper.setSubject(subject);
		
		FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));
		mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
		javaMailSender.send(mimeMessage);
		return "Mail with attachment sent successfully.";
	}
	
	public String sendMailWithOutAttachment(String toEmail, String body, String subject) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		
		
		mimeMessageHelper.setFrom("akashsahani.ss703@gmail.com");
		mimeMessageHelper.setTo(toEmail);
		mimeMessageHelper.setText(body);
		mimeMessageHelper.setSubject(subject);
		
//		FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));
//		mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
		javaMailSender.send(mimeMessage);
		return "Mail with attachment sent successfully.";
	}
	
	public String generatePasswordResetLink(String email, String currentStatus, String category) {
		
		if(category.equalsIgnoreCase("student") && currentStatus.equalsIgnoreCase("student")) {
			Student data = studentRepository.findByEmail(email);
			return "http://127.0.0.1:8080/student/student/" + data.getStudentId() + "/" + generateToken(email, data.getFirstName(), data.getRole());
		}else if (category.equalsIgnoreCase("student") && currentStatus.equalsIgnoreCase("applicant")) {
			StudentApplicant data = studentApplicantRepository.findByEmail(email);
			return "http://127.0.0.1:8080/student/applicant/" + data.getStudentApplicantId() + "/" + generateToken(email, data.getFirstName(), data.getRole());
		}else if(category.equalsIgnoreCase("teacher") && currentStatus.equalsIgnoreCase("teacher")) {
			Teacher data = teacherRepository.findByEmail(email);
			return "http://127.0.0.1:8080/teacher/teacher/" + data.getTeacherId() + "/" + generateToken(email, data.getFirstName(), data.getRole());
		}else if(category.equalsIgnoreCase("teacher") && currentStatus.equalsIgnoreCase("applicant")) {
			Teacher data = teacherRepository.findByEmail(email);
			return "http://127.0.0.1:8080/teacher/applicant/" + data.getTeacherId() + "/" + generateToken(email, data.getFirstName(), data.getRole());
		}else if(category.equalsIgnoreCase("employee") && currentStatus.equalsIgnoreCase("employee")) {
			Employee data = employeeRepository.findByEmail(email);
			return "http://127.0.0.1:8080/employee/employee/" + data.getEmployeeId() + "/" + generateToken(email, data.getFirstName(), data.getRole());
		}else if (category.equalsIgnoreCase("employee") && currentStatus.equalsIgnoreCase("applicant")) {
			Employee data = employeeRepository.findByEmail(email);
			return "http://127.0.0.1:8080/employee/applicant/" + data.getEmployeeId() + "/" + generateToken(email, data.getFirstName(), data.getRole());
		}else {
			return null;
		}
		
	}
	
	private String generateToken(String email, String firstName, Set<Role> role) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("email", email);
		claims.put("firstName", firstName);
		claims.put("roleName", role);
		
		return jwtUtil.createTokenForPasswordReset(claims, email);
	}
}
