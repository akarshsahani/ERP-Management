package com.example.demo.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.dto.request.PasswordResetEmailForm;
import com.example.demo.dto.request.PasswordResetPasswordForm;
import com.example.demo.service.MainService;

@Controller
public class MainController {

	@Autowired
	private MainService mainService;

	
	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody PasswordResetEmailForm email) throws MessagingException{
		return new ResponseEntity<String>(mainService.resetPassword(email.getEmail()), HttpStatus.OK);
	}
	
	@PostMapping("/reset-password/{category}/{currentStatus}/{id}/{token}")
	public ResponseEntity<String> resetPasswordUsingLink(@PathVariable String category, @PathVariable String currentStatus, @PathVariable Long id, @PathVariable String token, @RequestBody PasswordResetPasswordForm pwd) throws MessagingException{
		return new ResponseEntity<String>(mainService.resetPasswordUsingLink(category, currentStatus, id, pwd.getPassword(), pwd.getConfirmPassword(), token), HttpStatus.OK);
	}
	
}
