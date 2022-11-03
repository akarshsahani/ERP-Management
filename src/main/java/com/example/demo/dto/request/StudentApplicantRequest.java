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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StudentApplicantRequest {

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
	private String email;
	
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
	
	@NotBlank(message = "branch cannot be blank")
	@NotNull(message = "branch cannot be null")
	@NotEmpty(message = "branch cannot be empty")
	private String branch;
	
	@NotBlank(message = "programme cannot be blank")
	@NotNull(message = "programme cannot be null")
	@NotEmpty(message = "programme cannot be empty")
	private String programme;
	
	@NotBlank(message = "batch cannot be blank")
	@NotNull(message = "batch cannot be null")
	@NotEmpty(message = "batch cannot be empty")
	private String batch;
	
	@NotBlank(message = "password cannot be blank")
	@NotNull(message = "password cannot be null")
	@NotEmpty(message = "password cannot be empty")
	private String password;
	
}
