package com.example.demo.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
public class TeacherApplicant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long teacherApplicantId;
	private String firstName;
	private String lastName;
	private String middleName;
	private LocalDate dateOfBirth;
	private String phoneNumber;
	private String permanentAddress;
	private String currentAddress;
	
	@Column(unique = true)
	protected String personalEmail;
	
	private String tenthPercentage;
	private String tenthSchoolName;
	private String tenthSchoolAddress;
	private String tenthYearPassed;
	private String twelvethPercentage;
	private String twelvethSchoolName;
	private String twelvethSchoolAddress;
	private String twelvethYearPassed;
	private String bachelorPercentage;
	private String bachelorSchoolName;
	private String bachelorSchoolAddress;
	private String bachelorBranch;
	private String bachelorProgramme;
	private String bachelorYearPassed;
	private String mastersSchoolName;
	private String mastersSchoolAddress;
	private String mastersPercentage;
	private String mastersBranch;
	private String mastersProgramme;
	private String mastersYearPassed;
	private LocalDate appliedDate;
	private String password;
	private String category;
	private String designation;
//	private Set<Education> otherQualifications; 
	private String currentStatus;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "TEACHER_APPLICANT_ROLE",
	joinColumns = {
			@JoinColumn(name = "teacherApplicantId")
	},
	inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID")
	})
	private Set<Role> role;

}
