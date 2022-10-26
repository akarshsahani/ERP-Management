package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.request.LogInRequest;
import com.example.demo.service.JwtService;

@Controller
public class JwtController {

	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/authenticate")
	public ResponseEntity<String> authenticate(@RequestBody LogInRequest logInRequest) throws Exception {
		return new ResponseEntity<String>(jwtService.createJwtToken(logInRequest), HttpStatus.FOUND);
	}
}
