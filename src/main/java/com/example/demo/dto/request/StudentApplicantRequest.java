package com.example.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StudentApplicantRequest {

	
	private String firstName;
	private String lastName;
	private String middleName;
	private String dateOfBirth;
	private String phoneNumber;
	private String permanentAddress;
	private String currentAddress;
	private String email;
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
	private String password;
}
