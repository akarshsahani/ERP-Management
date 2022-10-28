package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.request.LogInRequest;
import com.example.demo.service.MainService;

@Controller
public class MainController {

	@Autowired
	private MainService mainService;
	
//	@PostMapping("/login/{category}/{currentStatus}")
//	public ResponseEntity<String> login(@RequestBody LogInRequest logInRequest, @PathVariable("category") String category, @PathVariable("currentStatus") String currentStatus){
//		mainService
//		return null;
//	}
}
