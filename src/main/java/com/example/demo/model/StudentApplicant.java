package com.example.demo.model;

import java.time.LocalDate;
import java.util.Set;

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
public class StudentApplicant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long studentApplicantId;
	private String firstName;
	private String lastName;
	private String middleName;
	private LocalDate dateOfBirth;
	private String phoneNumber;
	private String permanentAddress;
	private String currentAddress;
	public String email;
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
	private LocalDate appliedDate;
	public String password;
	private String category;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "STUDENT_APPLICANT_ROLE",
	joinColumns = {
			@JoinColumn(name = "studentApplicantId", referencedColumnName = "studentApplicantId")
	},
	inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID", referencedColumnName = "roleId")
	})
	public Set<Role> role;

}
