package com.example.demo.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

	@NotBlank(message = "firstName cannot be blank")
	@NotNull(message = "firstName cannot be null")
	@NotEmpty(message = "firstName cannot be empty")
	private String firstName;
	
	@NotBlank(message = "lastName cannot be blank")
	@NotNull(message = "lastName cannot be null")
	@NotEmpty(message = "lastName cannot be empty")
	private String lastName;
	
	private String middleName;
	
	@NotBlank(message = "dateOfBirth cannot be blank")
	@NotNull(message = "dateOfBirth cannot be null")
	@NotEmpty(message = "dateOfBirth cannot be empty")
	private String dateOfBirth;
	
	@NotBlank(message = "phoneNumber cannot be blank")
	@NotNull(message = "phoneNumber cannot be null")
	@NotEmpty(message = "phoneNumber cannot be empty")
	@Size(min = 10, max = 10, message = "please enter valid phone number")
	private String phoneNumber;
	
	@NotBlank(message = "permanentAddress cannot be blank")
	@NotNull(message = "permanentAddress cannot be null")
	@NotEmpty(message = "permanentAddress cannot be empty")
	private String permanentAddress;
	
	@NotBlank(message = "currentAddress cannot be blank")
	@NotNull(message = "currentAddress cannot be null")
	@NotEmpty(message = "currentAddress cannot be empty")
	private String currentAddress;
	
	@NotBlank(message = "email cannot be blank")
	@NotNull(message = "email cannot be null")
	@NotEmpty(message = "email cannot be empty")
	@Email(message = "Invalid email.")
	protected String email;
	
	@NotBlank(message = "tenthPercentage cannot be blank")
	@NotNull(message = "tenthPercentage cannot be null")
	@NotEmpty(message = "tenthPercentage cannot be empty")
	private String tenthPercentage;
	
	@NotBlank(message = "tenthSchoolName cannot be blank")
	@NotNull(message = "tenthSchoolName cannot be null")
	@NotEmpty(message = "tenthSchoolName cannot be empty")
	private String tenthSchoolName;
	
	@NotBlank(message = "tenthSchoolAddress cannot be blank")
	@NotNull(message = "tenthSchoolAddress cannot be null")
	@NotEmpty(message = "tenthSchoolAddress cannot be empty")
	private String tenthSchoolAddress;
	
	@NotBlank(message = "tenthYearPassed cannot be blank")
	@NotNull(message = "tenthYearPassed cannot be null")
	@NotEmpty(message = "tenthYearPassed cannot be empty")
	private String tenthYearPassed;
	
	@NotBlank(message = "twelvethPercentage cannot be blank")
	@NotNull(message = "twelvethPercentage cannot be null")
	@NotEmpty(message = "twelvethPercentage cannot be empty")
	private String twelvethPercentage;
	
	@NotBlank(message = "twelvethSchoolName cannot be blank")
	@NotNull(message = "twelvethSchoolName cannot be null")
	@NotEmpty(message = "twelvethSchoolName cannot be empty")
	private String twelvethSchoolName;
	
	@NotBlank(message = "twelvethSchoolAddress cannot be blank")
	@NotNull(message = "twelvethSchoolAddress cannot be null")
	@NotEmpty(message = "twelvethSchoolAddress cannot be empty")
	private String twelvethSchoolAddress;
	
	@NotBlank(message = "twelvethYearPassed cannot be blank")
	@NotNull(message = "twelvethYearPassed cannot be null")
	@NotEmpty(message = "twelvethYearPassed cannot be empty")
	private String twelvethYearPassed;
	
	@NotBlank(message = "bachelorPercentage cannot be blank")
	@NotNull(message = "bachelorPercentage cannot be null")
	@NotEmpty(message = "bachelorPercentage cannot be empty")
	private String bachelorPercentage;
	
	@NotBlank(message = "bachelorSchoolName cannot be blank")
	@NotNull(message = "bachelorSchoolName cannot be null")
	@NotEmpty(message = "bachelorSchoolName cannot be empty")
	private String bachelorSchoolName;
	
	@NotBlank(message = "bachelorSchoolAddress cannot be blank")
	@NotNull(message = "bachelorSchoolAddress cannot be null")
	@NotEmpty(message = "bachelorSchoolAddress cannot be empty")
	private String bachelorSchoolAddress;
	
	@NotBlank(message = "bachelorBranch cannot be blank")
	@NotNull(message = "bachelorBranch cannot be null")
	@NotEmpty(message = "bachelorBranch cannot be empty")
	private String bachelorBranch;
	
	@NotBlank(message = "bachelorProgramme cannot be blank")
	@NotNull(message = "bachelorProgramme cannot be null")
	@NotEmpty(message = "bachelorProgramme cannot be empty")
	private String bachelorProgramme;
	
	@NotBlank(message = "bachelorYearPassed cannot be blank")
	@NotNull(message = "bachelorYearPassed cannot be null")
	@NotEmpty(message = "bachelorYearPassed cannot be empty")
	private String bachelorYearPassed;
	
	@NotBlank(message = "mastersSchoolName cannot be blank")
	@NotNull(message = "mastersSchoolName cannot be null")
	@NotEmpty(message = "mastersSchoolName cannot be empty")
	private String mastersSchoolName;
	
	@NotBlank(message = "mastersSchoolAddress cannot be blank")
	@NotNull(message = "mastersSchoolAddress cannot be null")
	@NotEmpty(message = "mastersSchoolAddress cannot be empty")
	private String mastersSchoolAddress;
	
	@NotBlank(message = "mastersPercentage cannot be blank")
	@NotNull(message = "mastersPercentage cannot be null")
	@NotEmpty(message = "mastersPercentage cannot be empty")
	private String mastersPercentage;
	
	@NotBlank(message = "mastersBranch cannot be blank")
	@NotNull(message = "mastersBranch cannot be null")
	@NotEmpty(message = "mastersBranch cannot be empty")
	private String mastersBranch;
	
	@NotBlank(message = "mastersProgramme cannot be blank")
	@NotNull(message = "mastersProgramme cannot be null")
	@NotEmpty(message = "mastersProgramme cannot be empty")
	private String mastersProgramme;
	
	@NotBlank(message = "mastersYearPassed cannot be blank")
	@NotNull(message = "mastersYearPassed cannot be null")
	@NotEmpty(message = "mastersYearPassed cannot be empty")
	private String mastersYearPassed;
	
	@NotBlank(message = "password cannot be blank")
	@NotNull(message = "password cannot be null")
	@NotEmpty(message = "password cannot be empty")
	private String password;
	
	@NotBlank(message = "designation cannot be blank")
	@NotNull(message = "designation cannot be null")
	@NotEmpty(message = "designation cannot be empty")
	private String designation;
	
//	private Set<Education> otherQualifications; 

}
