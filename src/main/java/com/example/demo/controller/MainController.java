package com.example.demo.controller;

import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.dto.request.PasswordResetEmailForm;
import com.example.demo.dto.request.PasswordResetPasswordForm;
import com.example.demo.service.MainService;

@Controller
@Validated
public class MainController {

	@Autowired
	private MainService mainService;

	
	@GetMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody @Valid PasswordResetEmailForm email) throws MessagingException{
		return new ResponseEntity<String>(mainService.resetPassword(email.getEmail()), HttpStatus.OK);
	}
	
	@PostMapping("/reset-password/{category}/{currentStatus}/{id}/{token}")
	public ResponseEntity<String> resetPasswordUsingLink(@PathVariable @NotBlank @NotNull @NotEmpty String category, 
			@PathVariable @NotBlank @NotNull @NotEmpty String currentStatus, 
			@PathVariable @NotBlank @NotNull @NotEmpty Long id, 
			@PathVariable @NotBlank @NotNull @NotEmpty String token, 
			@RequestBody @Valid PasswordResetPasswordForm pwd) throws MessagingException{
		return new ResponseEntity<String>(mainService.resetPasswordUsingLink(category, currentStatus, id, pwd.getPassword(), pwd.getConfirmPassword(), token), HttpStatus.OK);
	}
	
}
