package com.example.demo.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Marks {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String marksId;
	private String courseCode;
	private String registrationNumber;
	private String studentName;
	private String courseName;
	private int courseCredit;
	private String courseCategory;
	private Long teacherId;
	private String teacherName;
	private String slot;
	
	private float unitTestOne;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private LocalDate unitTestOneMarksDate;
	
	private float midTestOne;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private LocalDate midTestOneMarksDate;
	
	private float assignmentOne;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private LocalDate assignmentOneMarksDate;
	
	private float assignmentTwo;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private LocalDate assignmentTwoMarksDate;
	
	private float unitTestTwo;
	private LocalDate unitTestTwoMarksDate;
	
	private float midTestTwo;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private LocalDate midTestTwoMarksDate;
	
	private float internalMarks;
	
	private float endSemesterMarks;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private LocalDate endSemesterMarksDate;
	
	private float finalMarks;
	
	private String semester;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private String examYearAndMonth;

}
