package com.example.demo.dto.response;

import com.example.demo.model.StudentApplicant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StudentApplicantLoginResponse {
	
	private StudentApplicant studentApplicant;
	private String jwtToken;
	private String message;

}
