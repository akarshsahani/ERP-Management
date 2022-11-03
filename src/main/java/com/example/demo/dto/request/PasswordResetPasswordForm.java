package com.example.demo.dto.request;

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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordResetPasswordForm {

	@NotBlank(message = "password cannot be blank")
	@NotNull(message = "password cannot be null")
	@NotEmpty(message = "password cannot be empty")
	private String password;
	
	@NotBlank(message = "confirmPassword cannot be blank")
	@NotNull(message = "confirmPassword cannot be null")
	@NotEmpty(message = "confirmPassword cannot be empty")
	private String confirmPassword;
}
