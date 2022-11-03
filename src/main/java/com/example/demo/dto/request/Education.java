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
public class Education {

	private String level;
	private String fieldOfStudy;
	private String schoolName;
	private String schoolAddress;
	private String grade;
	private String passedYear;
}
