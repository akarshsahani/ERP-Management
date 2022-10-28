package com.example.demo.dto.response;

import com.example.demo.model.Teacher;

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
public class TeacherLoginResponse {

	
	private Teacher teacher;
	private String jwtToken;
	private String message;
}
