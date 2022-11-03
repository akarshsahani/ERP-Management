package com.example.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherApplicantRequest {

	private String firstName;
	private String lastName;
	private String middleName;
	private String dateOfBirth;
	private String phoneNumber;
	private String permanentAddress;
	private String currentAddress;
	protected String email;
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
	private String password;
	private String designation;
//	private Set<Education> otherQualifications; 

}
