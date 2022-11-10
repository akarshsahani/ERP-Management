package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentAttendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long attendanceId;
	
	private String registrationNumber;
	private Long teacherId;
	private String courseCode;
	private String slot;
	
	private String createdDate;
	private String attendanceDate;
	private String classTime;
	
	private String status;

}
