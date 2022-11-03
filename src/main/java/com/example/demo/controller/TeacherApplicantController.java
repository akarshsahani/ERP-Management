package com.example.demo.controller;

import java.security.Principal;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.request.LogInRequest;
import com.example.demo.dto.request.TeacherApplicantRequest;
import com.example.demo.dto.response.TeacherApplicantLoginResponse;
import com.example.demo.model.TeacherApplicant;
import com.example.demo.service.TeacherApplicantService;

@Controller
public class TeacherApplicantController {

	@Autowired
	private TeacherApplicantService teacherApplicantService;
	
	@PostMapping("/apply/teacher")
	public ResponseEntity<String> newTeacherApplicant(@RequestBody TeacherApplicantRequest teacherApplicantRequest) throws MessagingException{
		return new ResponseEntity<String>(teacherApplicantService.newTeacherApplicant(teacherApplicantRequest), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/login/teacher/applicant")
	public ResponseEntity<TeacherApplicantLoginResponse> teacherApplicantLogin(@RequestBody LogInRequest logInRequest) throws Exception{
		return new ResponseEntity<TeacherApplicantLoginResponse>(teacherApplicantService.teacherApplicantLogin(logInRequest), HttpStatus.OK);
	}
	
	@GetMapping("/teacher/applicant/detail")
	@PreAuthorize("hasRole('APPLICANT')")
	public ResponseEntity<TeacherApplicant> teacherApplicantDetail(@RequestBody Principal principal){
		return new ResponseEntity<TeacherApplicant>(teacherApplicantService.teacherApplicantDetail(principal), HttpStatus.FOUND);
	}
}
