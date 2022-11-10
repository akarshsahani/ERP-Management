package com.example.demo.dto.response;

import java.util.List;

import com.example.demo.model.StudentAttendance;

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
public class StudentAttendanceResponse {

	private List<StudentAttendance> attendance;
	private String messge;
}
