package com.example.demo.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.LogInRequest;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeApplicant;
import com.example.demo.model.Student;
import com.example.demo.model.StudentApplicant;
import com.example.demo.model.Teacher;
import com.example.demo.model.TeacherApplicant;
import com.example.demo.repository.EmployeeApplicantRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.StudentApplicantRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherApplicantRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.util.JwtUtil;

@Service
public class JwtService implements UserDetailsService{
	
	@Autowired
	private StudentApplicantRepository studentApplicantRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private TeacherApplicantRepository teacherApplicantRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeApplicantRepository employeeApplicantRepository;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public String createJwtToken(LogInRequest logInRequest) throws Exception {
		authenticate(logInRequest.getEmail(), logInRequest.getPassword());
		UserDetails userDetails = loadUserByUsername(logInRequest.getEmail());
		String newGeneratedToken = jwtUtil.generateToken(userDetails);
		return newGeneratedToken;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if (studentRepository.findByEmail(username) != null) {
			Student student = studentRepository.findByEmail(username);
			return new org.springframework.security.core.userdetails.User(
					student.getEmail(),
					student.getPassword(),
					getStudentAuthorities(student));
		}
		else if (teacherRepository.findByEmail(username) != null) {
			Teacher teacher = teacherRepository.findByEmail(username);
			return new org.springframework.security.core.userdetails.User(
					teacher.getEmail(),
					teacher.getPassword(),
					getTeacherAuthorities(teacher));
		}
		else if (employeeRepository.findByEmail(username) != null) {
			Employee employee = employeeRepository.findByEmail(username);
			return new org.springframework.security.core.userdetails.User(
					employee.getEmail(),
					employee.getPassword(),
					getEmployeeAuthorities(employee));
		}
		else if (studentApplicantRepository.findByEmail(username) != null) {
			StudentApplicant studentApplicant = studentApplicantRepository.findByEmail(username);
			return new org.springframework.security.core.userdetails.User(
					studentApplicant.getEmail(),
					studentApplicant.getPassword(),
					getStudentApplicantAuthorities(studentApplicant));
		}
		else if (teacherApplicantRepository.findByEmail(username) != null) {
			TeacherApplicant teacherApplicant = teacherApplicantRepository.findByEmail(username);
			return new org.springframework.security.core.userdetails.User(
					teacherApplicant.getEmail(),
					teacherApplicant.getPassword(),
					getTeacherApplicantAuthorities(teacherApplicant));
		}
		else if (employeeApplicantRepository.findByEmail(username) != null) {
			EmployeeApplicant employeeApplicant = employeeApplicantRepository.findByEmail(username);
			return new org.springframework.security.core.userdetails.User(
					employeeApplicant.getEmail(),
					employeeApplicant.getPassword(),
					getEmployeeApplicantAuthorities(employeeApplicant));
		}
		else {
			throw new UsernameNotFoundException("Your are not registered. Please SignUp to login.");
		}
	}
	
	private Set<SimpleGrantedAuthority> getStudentAuthorities(Student student){
		Set <SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
		student.getRole().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		});
		return authorities;
	}
	
	private Set<SimpleGrantedAuthority> getStudentApplicantAuthorities(StudentApplicant studentApplicant){
		Set <SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
		studentApplicant.getRole().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		});
		return authorities;
	}
	
	private Set<SimpleGrantedAuthority> getTeacherAuthorities(Teacher teacher){
		Set <SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
		teacher.getRole().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		});
		return authorities;
	}
	
	private Set<SimpleGrantedAuthority> getTeacherApplicantAuthorities(TeacherApplicant teacherApplicant){
		Set <SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
		teacherApplicant.getRole().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		});
		return authorities;
	}
	
	private Set<SimpleGrantedAuthority> getEmployeeAuthorities(Employee employee){
		Set <SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
		employee.getRole().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		});
		return authorities;
	}
	
	private Set<SimpleGrantedAuthority> getEmployeeApplicantAuthorities(EmployeeApplicant employeeApplicant){
		Set <SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
		employeeApplicant.getRole().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		});
		return authorities;
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("User is disabled.");
		} catch (BadCredentialsException e) {
			throw new Exception("Bad credentials from user.");
		} catch (LockedException e) {
			throw new Exception("User is locked.");
		}
	}

}
