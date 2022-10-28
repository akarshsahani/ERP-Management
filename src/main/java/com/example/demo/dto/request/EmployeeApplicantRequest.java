package com.example.demo.dto.request;

import java.util.HashMap;

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

	private String firstName;
	private String lastName;
	private String middleName;
	private String dateOfBirth;
	private String phoneNumber;
	private String permanentAddress;
	private String currentAddress;
	private String email;
	private String password;
	private HashMap<String, String> qualification;
	private String currentStatus;
	private String designation;
	
}
