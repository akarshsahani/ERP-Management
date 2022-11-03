package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.request.LogInRequest;
import com.example.demo.dto.response.TeacherLoginResponse;
import com.example.demo.model.Teacher;
import com.example.demo.service.TeacherService;

@Controller
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	
	@PostMapping("/login/teacher")
	public ResponseEntity<TeacherLoginResponse> loginTeacher(@RequestBody LogInRequest logInRequest) throws Exception{
		return new ResponseEntity<TeacherLoginResponse>(teacherService.teacherLogin(logInRequest), HttpStatus.FOUND);
	}
	
	@PostMapping("/teacher/detail")
	@PreAuthorize("hasRole('TEACHER')")
	public ResponseEntity<Teacher> teacherDetail(Principal principal){
		return new ResponseEntity<Teacher>(teacherService.teacherDetail(principal), HttpStatus.OK);
	}
}
