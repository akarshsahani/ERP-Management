package com.example.demo.dto.response;

import java.util.List;

import com.example.demo.model.Marks;

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
public class MarksOperationResponse {


	private List<Marks> marks;
	private String message;
}
