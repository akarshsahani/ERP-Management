package com.example.demo.dto.response;

import com.example.demo.model.EmployeeApplicant;

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
public class EmployeeApplicantLoginResponse {
	
	private EmployeeApplicant employeeApplicant;
	private String jwtToken;
	private String message;

}
