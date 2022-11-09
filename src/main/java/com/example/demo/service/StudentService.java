package com.example.demo.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.request.LogInRequest;
import com.example.demo.dto.response.StudentLoginResponse;
import com.example.demo.model.Marks;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.MarksRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.util.Util;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private MarksRepository marksRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private Util util;
	
	
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
	
	public String enrollCourse(String courseCode, Long teacherId, String slot, Principal principal) {
		Student student = studentRepository.findByOfficialEmail(principal.getName());
		Teacher teacher = teacherRepository.findByTeacherId(teacherId);
		Marks marks = new Marks();
		if(marksRepository.findByMarksId(util.returnMarksId(student.getRegistrationNumber(), courseCode)) == null) {
			marks = Marks.builder()
						 .studentName(util.returnFullName(student.getFirstName(), student.getMiddleName(), student.getLastName()))
						 .registrationNumber(student.getRegistrationNumber())
						 .courseCode(courseCode)
						 .courseName(courseRepository.returnCourseName(courseCode))
						 .marksId(util.returnMarksId(student.getRegistrationNumber(), courseCode))
						 .teacherId(teacherId)
						 .teacherName(util.returnFullName(teacher.getFirstName(), teacher.getMiddleName(), teacher.getLastName()))
						 .slot(slot)
						 .semester(student.getCurrentSemester())
						 .build();
			marksRepository.save(marks);
			return "Course enrolled successfully";
		}else {
			return "Course already enrolled";
		}
	}
	
	@Transactional
	public String unenrollCourse(String courseCode, Long teacherId, String slot, Principal principal) {
		Student student = studentRepository.findByOfficialEmail(principal.getName());
		Marks marks = marksRepository.findByMarksId(util.returnMarksId(student.getRegistrationNumber(), courseCode));
		if(marks != null && marks.getTeacherId() == teacherId && marks.getSlot().equalsIgnoreCase(slot) && marks.getCourseCode().equalsIgnoreCase(courseCode)){
			marksRepository.deleteByMarksId(util.returnMarksId(student.getRegistrationNumber(), courseCode));
			return "unenrollement successful";
		}else {
			return "please register the course to unenroll.";
		}
	}
	
	

}
