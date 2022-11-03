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
public class EmployeeApplicantRequest {

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
	
	@NotBlank(message = "firstName cannot be blank")
	@NotNull(message = "firstName cannot be null")
	@NotEmpty(message = "firstName cannot be empty")
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
	
	@NotBlank(message = "password cannot be blank")
	@NotNull(message = "password cannot be null")
	@NotEmpty(message = "password cannot be empty")
	private String password;
	
//	private Set<Education> qualification;
//	private String currentStatus;
	
	@NotBlank(message = "designation cannot be blank")
	@NotNull(message = "designation cannot be null")
	@NotEmpty(message = "designation cannot be empty")
	private String designation;
	
}
