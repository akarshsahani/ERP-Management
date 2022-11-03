package com.example.demo.controller;

import java.security.Principal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.request.LogInRequest;
import com.example.demo.dto.response.TeacherLoginResponse;
import com.example.demo.model.Teacher;
import com.example.demo.service.TeacherService;

@Controller
@Validated
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	
	@GetMapping("/login/teacher")
	public ResponseEntity<TeacherLoginResponse> loginTeacher(@RequestBody @Valid LogInRequest logInRequest) throws Exception{
		return new ResponseEntity<TeacherLoginResponse>(teacherService.teacherLogin(logInRequest), HttpStatus.FOUND);
	}
	
	@GetMapping("/teacher/detail")
	@PreAuthorize("hasRole('TEACHER')")
	public ResponseEntity<Teacher> teacherDetail(@NotBlank @NotNull @NotEmpty Principal principal){
		return new ResponseEntity<Teacher>(teacherService.teacherDetail(principal), HttpStatus.OK);
	}
}
