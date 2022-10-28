package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.LogInRequest;
import com.example.demo.repository.EmployeeApplicantRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.StudentApplicantRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherApplicantRepository;
import com.example.demo.repository.TeacherRepository;
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
	private RoleRepository roleRepository;
	
	@Autowired
	private Util util;
	
//	public String login(LogInRequest logInRequest, String category, String currentStatus) {
//		if
//	}
}
