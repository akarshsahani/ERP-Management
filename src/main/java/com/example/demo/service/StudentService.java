package com.example.demo.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.LogInRequest;
import com.example.demo.dto.response.StudentLoginResponse;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private JwtService jwtService;
	
	
	public StudentLoginResponse studentLogin(LogInRequest logInRequest) throws Exception {
		Student student = studentRepository.findByOfficialEmail(logInRequest.getEmail());
		if(student != null) {
			String jwtToken = jwtService.createJwtToken(logInRequest);
			String message = "Login Successful!";
			return new StudentLoginResponse(student, jwtToken, message);
		}else {
			return new StudentLoginResponse(student, null, "Email not registered.");
		}
		
	}

	
	public Student studentDetail(Principal principal) {
		return studentRepository.findByOfficialEmail(principal.getName());
	}
	
	

}
