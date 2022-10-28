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
		String jwtToken = jwtService.createJwtToken(logInRequest);
		Student student = studentRepository.findByEmail(logInRequest.getEmail());
		String message = "Login Successful!";
		
		return new StudentLoginResponse(student, jwtToken, message);
	}

	
	public Student studentDetails(Principal principal) {
		return studentRepository.findByEmail(principal.getName());
	}
	
	

}
