package com.example.demo.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
public class LogInRequest {
	
	@NotBlank(message = "email cannot be blank")
	@NotNull(message = "email cannot be null")
	@NotEmpty(message = "email cannot be empty")
	@Email(message = "Invalid email.")
	private String email;
	
	@NotBlank(message = "password cannot be blank")
	@NotNull(message = "password cannot be null")
	@NotEmpty(message = "password cannot be empty")
	private String password;

}
