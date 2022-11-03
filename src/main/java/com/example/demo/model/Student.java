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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long studentId;
	
	@Column(unique = true)
	private String registrationNumber;
	private String firstName;
	private String lastName;
	private String middleName;
	private LocalDate dateOfBirth;
	private String phoneNumber;
	private String permanentAddress;
	private String currentAddress;
	
	@Column(unique = true)
	protected String personalEmail;
	
	@Column(unique = true)
	private String officialEmail;
	
	private String tenthPercentage;
	private String tenthSchoolName;
	private String tenthSchoolAddress;
	private String tenthYearPassed;
	private String twelvethPercentage;
	private String twelvethSchoolName;
	private String twelvethSchoolAddress;
	private String twelvethYearPassed;
	private String branch;
	private String programme;
	private String batch;
	private String currentStatus;
	private String currentSemester;
	private String currentYear;
	private LocalDate admissionDate;
	private LocalDate appliedDate;
	private String password;
	private String category;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "STUDENT_ROLE",
	joinColumns = {
			@JoinColumn(name = "studentId", referencedColumnName = "studentId")
	},
	inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID", referencedColumnName = "roleId")
	})
	private Set<Role> role;

}
