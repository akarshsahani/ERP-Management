package com.example.demo.global_exception_handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleInvalidArgument(MethodArgumentNotValidException exception){
		Map<String, String> errorMap = new HashMap<String, String>();
		exception.getBindingResult().getFieldErrors().forEach(error ->{
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return new ResponseEntity<Map<String,String>>(errorMap, HttpStatus.BAD_REQUEST);
	}
	
	

}
