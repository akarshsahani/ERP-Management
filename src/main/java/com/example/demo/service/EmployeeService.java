package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.model.StudentApplicant;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.StudentApplicantRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired 
	private StudentApplicantRepository studentApplicantRepository;
	
	public List<StudentApplicant> allStudentApplicant(){
		return studentApplicantRepository.findAll();
	}
	
	public Student approveStudentApplicant(Long id) {
		StudentApplicant applicant = studentApplicantRepository.findById(id);
		applicant.setCurrentStatus("STUDENT");
		Student.builder().
		return null;
	}
}
