package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import javax.mail.MessagingException;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.request.LogInRequest;
import com.example.demo.dto.response.EmployeeLoginResponse;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeApplicant;
import com.example.demo.model.Student;
import com.example.demo.model.StudentApplicant;
import com.example.demo.model.Teacher;
import com.example.demo.model.TeacherApplicant;
import com.example.demo.service.EmployeeService;


@Controller
@Validated
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/login/employee")
	public ResponseEntity<EmployeeLoginResponse> loginEmployee(@RequestBody @Valid LogInRequest logInRequest) throws Exception{
		return new ResponseEntity<EmployeeLoginResponse>(employeeService.employeeLogin(logInRequest), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/employee/detail")
	@PreAuthorize("hasRole('EMPLOYEE')")
	public ResponseEntity<Employee> employeeDetail(@NotBlank @NotNull @NotEmpty Principal principal){
		return new ResponseEntity<Employee>(employeeService.employeeDetail(principal), HttpStatus.FOUND);
	}
		
	@GetMapping("/list/student/applicant")
	@PreAuthorize("hasRole('EMPLOYEE')")
	public ResponseEntity<List<StudentApplicant>> listOfStudentApplicant(){
		return new ResponseEntity<List<StudentApplicant>>(employeeService.allStudentApplicant(), HttpStatus.FOUND);
	}
	
	@GetMapping("/list/teacher/applicant")
	@PreAuthorize("hasRole('EMPLOYEE')")
	public ResponseEntity<List<TeacherApplicant>> listOfTeacherApplicant(){
		return new ResponseEntity<List<TeacherApplicant>>(employeeService.allTeacherApplicant(), HttpStatus.FOUND);
	}
	
	@GetMapping("/list/employee/applicant")
	@PreAuthorize("hasRole('EMPLOYEE')")
	public ResponseEntity<List<EmployeeApplicant>> listOfEmployeeApplicant(){
		return new ResponseEntity<List<EmployeeApplicant>>(employeeService.allEmployeeApplicant(), HttpStatus.FOUND);
	}
	
	@GetMapping("/list/student")
	@PreAuthorize("hasRole('EMPLOYEE')")
	public ResponseEntity<List<Student>> listOfStudent(){
		return new ResponseEntity<List<Student>>(employeeService.allStudent(), HttpStatus.FOUND);
	}
	
	@GetMapping("/list/teacher")
	@PreAuthorize("hasRole('EMPLOYEE')")
	public ResponseEntity<List<Teacher>> listOfTeacher(){
		return new ResponseEntity<List<Teacher>>(employeeService.allTeacher(), HttpStatus.FOUND);
	}
	
	@GetMapping("/list/employee")
	@PreAuthorize("hasRole('EMPLOYEE')") 
	public ResponseEntity<List<Employee>> listOfEmployee(){
		return new ResponseEntity<List<Employee>>(employeeService.allEmployee(), HttpStatus.FOUND);
	}
	
	@PostMapping("/{category}/approve")
	@PreAuthorize("hasRole('EMPLOYEE')")
	public ResponseEntity<String> approveTeacherApplicant(@RequestParam(value ="id") @NotBlank @NotNull @NotEmpty Long id, @PathVariable @NotBlank @NotNull @NotEmpty String category) throws MessagingException{
		if(category.equalsIgnoreCase("teacher")) {
			return new ResponseEntity<String>(employeeService.approveTeacherApplicant(id), HttpStatus.OK);
		}else if(category.equalsIgnoreCase("student")){
			return new ResponseEntity<String>(employeeService.approveStudentApplicant(id), HttpStatus.OK);
		}else if(category.equalsIgnoreCase("employee")) {
			return new ResponseEntity<String>(employeeService.approveEmployeeApplicant(id), HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Something went wrong. Please try again!", HttpStatus.BAD_REQUEST);
		}
	}
}
