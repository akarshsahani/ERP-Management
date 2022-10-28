package com.example.demo.dto.response;

import com.example.demo.model.TeacherApplicant;

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
public class TeacherApplicantLoginResponse {

	private TeacherApplicant teacherApplicant;
	private String jwtToken;
	private String message;
}
