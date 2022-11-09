package com.example.demo.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.request.UpdateMarksRequest;
import com.example.demo.dto.response.MarksOperationResponse;
import com.example.demo.service.MarksServices;

@Controller
public class MarksController {

	@Autowired
	private MarksServices marksServices;
	
	@GetMapping("/marks/create-field/")
	public ResponseEntity<MarksOperationResponse> createFieldsToEnterMarks(@RequestParam String examName, @RequestParam Long teacherId, @RequestParam String slot, @RequestParam String courseCode){
		return new ResponseEntity<MarksOperationResponse>(marksServices.createListOfStudentsToEnterMarksInSpecificExam(examName, courseCode, slot, teacherId), HttpStatus.OK);
	}
	
	@PostMapping("/update-marks/")
	public ResponseEntity<MarksOperationResponse> updateMarks(@RequestBody ArrayList<UpdateMarksRequest> listOfMarksToBeUpdated,@RequestParam String category, @RequestParam String examName, @RequestParam Long teacherId, @RequestParam String slot, @RequestParam String courseCode){
		return new ResponseEntity<MarksOperationResponse>(marksServices.updatesMarks(listOfMarksToBeUpdated, examName, slot, courseCode, teacherId, category), HttpStatus.OK);
	}
	
	
	
	
}
