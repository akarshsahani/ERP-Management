package com.example.demo.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.LogInRequest;
import com.example.demo.dto.response.TeacherLoginResponse;
import com.example.demo.model.Teacher;
import com.example.demo.repository.TeacherRepository;

@Service
public class TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private JwtService jwtService;
	
	public TeacherLoginResponse teacherLogin(LogInRequest logInRequest) throws Exception {
		String jwtToken = jwtService.createJwtToken(logInRequest);
		Teacher teacher = teacherRepository.findByEmail(logInRequest.getEmail());
		String message = "Login Successful!";
		
		return new TeacherLoginResponse(teacher, jwtToken, message);
	}

	
	public Teacher teacherDetails(Principal principal) {
		return teacherRepository.findByEmail(principal.getName());
	}
}
