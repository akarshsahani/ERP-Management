package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.request.UpdateMarksRequest;
import com.example.demo.dto.response.MarksOperationResponse;
import com.example.demo.model.Marks;
import com.example.demo.repository.MarksRepository;
import com.example.demo.util.Util;

@Service
public class MarksServices {
	
	@Autowired
	private MarksRepository marksRepository;
	
	@Autowired
	private Util util;
	
	public List<Marks> listOfMarks(String registrationNumber, String courseCode){
		if(registrationNumber != null && courseCode != null) {
			return marksRepository.getListOfMarksByRegistrationNumberAndCourseCode(registrationNumber, courseCode);
		}else if(registrationNumber != null && courseCode == null) {
			return marksRepository.getListOfMarksByRegistrationNumber(registrationNumber);
		}else if(registrationNumber == null && courseCode != null) {
			return marksRepository.getListOfMarksByCourseCode(courseCode);
		}else {
			return null;
		}
	}
	
	@Transactional
	public List<Marks> listOfStudentsRegisteredUnderSpecificTeacherAndCourse(String slot, String courseCode, Long teacherId){
		return marksRepository.listOfStudentsRegisteredUnderSpecificTeacherAndCourse(teacherId, courseCode, slot);
	}
	
	public MarksOperationResponse createListOfStudentsToEnterMarksInSpecificExam(String examName, String courseCode, String slot, Long teacherId) {
		LocalDate date = LocalDate.now();
		List<Marks> listOfMarks = listOfStudentsRegisteredUnderSpecificTeacherAndCourse(slot, courseCode, teacherId);
		if(examName.equalsIgnoreCase("unitTestOne")) {
			for (Marks marks : listOfMarks) {
				marksRepository.updateUnitTestOneDate(date.toString(), marks.getMarksId());
			}
			return new MarksOperationResponse (listOfStudentsRegisteredUnderSpecificTeacherAndCourse(slot, courseCode, teacherId), "Created successfully.");
		}
		else if(examName.equalsIgnoreCase("midTestOne")) {
			for (Marks marks : listOfMarks) {
				marksRepository.updateMidTestOneDate(date.toString(), marks.getMarksId());
			}
			return new MarksOperationResponse (listOfStudentsRegisteredUnderSpecificTeacherAndCourse(slot, courseCode, teacherId), "Created successfully.");
		}
		else if(examName.equalsIgnoreCase("unitTestTwo")) {
			for (Marks marks : listOfMarks) {
				marksRepository.updateUnitTestTwoDate(date.toString(), marks.getMarksId());
			}
			return new MarksOperationResponse (listOfStudentsRegisteredUnderSpecificTeacherAndCourse(slot, courseCode, teacherId), "Created successfully.");
		}
		else if(examName.equalsIgnoreCase("midTestTwo")) {
			for (Marks marks : listOfMarks) {
				marksRepository.updateMidTestTwoDate(date.toString(), marks.getMarksId());
			}
			return new MarksOperationResponse (listOfStudentsRegisteredUnderSpecificTeacherAndCourse(slot, courseCode, teacherId), "Created successfully.");
		}
		else if(examName.equalsIgnoreCase("assignmentOne")) {
			for (Marks marks : listOfMarks) {
				marksRepository.updateAssignmentOneMarksDate(date.toString(), marks.getMarksId());
			}
			return new MarksOperationResponse (listOfStudentsRegisteredUnderSpecificTeacherAndCourse(slot, courseCode, teacherId), "Created successfully.");
		}
		else if(examName.equalsIgnoreCase("assignmentTwo")) {
			for (Marks marks : listOfMarks) {
				marksRepository.updateAssignmentTwoMarksDate(date.toString(), marks.getMarksId());
			}
			return new MarksOperationResponse (listOfStudentsRegisteredUnderSpecificTeacherAndCourse(slot, courseCode, teacherId), "Created successfully.");
		}
		else {
			return new MarksOperationResponse (null, "Something went wrong.");
		}		
	}
	
	public MarksOperationResponse updatesMarks(ArrayList<UpdateMarksRequest> listOfMarksToBeUpdated, String examName, String slot, String courseCode, Long teacherId, String category) {
		int i =0;
		if(category.equalsIgnoreCase("employee")) {
			if(examName.equalsIgnoreCase("unitTestOne")) {
				for (UpdateMarksRequest marks : listOfMarksToBeUpdated) {
					marksRepository.updateUnitTestOneMarks(marks.getMarks(), marks.getMarksId());
				}
			}
			else if(examName.equalsIgnoreCase("midTestOne")) {
				for (UpdateMarksRequest marks : listOfMarksToBeUpdated) {
					marksRepository.updateMidTestOneMarks(marks.getMarks(), marks.getMarksId());
				}
			}
			else if(examName.equalsIgnoreCase("unitTestTwo")) {
				for (UpdateMarksRequest marks : listOfMarksToBeUpdated) {
					marksRepository.updateUnitTestTwoMarks(marks.getMarks(), marks.getMarksId());
				}
			}
			else if(examName.equalsIgnoreCase("midTestTwo")) {
				for (UpdateMarksRequest marks : listOfMarksToBeUpdated) {
					marksRepository.updateMidTestTwoMarks(marks.getMarks(), marks.getMarksId());
				}
			}
			else if(examName.equalsIgnoreCase("assignmentOne")) {
				for (UpdateMarksRequest marks : listOfMarksToBeUpdated) {
					marksRepository.updateAssignmentOneMarks(marks.getMarks(), marks.getMarksId());
				}
			}
			else if(examName.equalsIgnoreCase("assignmentTwo")) {
				for (UpdateMarksRequest marks : listOfMarksToBeUpdated) {
					marksRepository.updateAssignmentTwoMarks(marks.getMarks(), marks.getMarksId());
				}
			}
			else if(examName.equalsIgnoreCase("endSemester")) {
				for (UpdateMarksRequest marks : listOfMarksToBeUpdated) {
					marksRepository.updateEndSemesterMarks(marks.getMarks(), marks.getMarksId());
				}
			}
			return new MarksOperationResponse (listOfStudentsRegisteredUnderSpecificTeacherAndCourse(slot, courseCode, teacherId), "Created successfully.");
		}
		else if(category.equalsIgnoreCase("teacher")){
			if(examName.equalsIgnoreCase("unitTestOne")) {
				for (UpdateMarksRequest marks : listOfMarksToBeUpdated) {
					if(util.getNumberOfDaysBetweenCreatedDateAndCurrentDate(marks.getDate()) <= 1) {
						marksRepository.updateUnitTestOneMarks(marks.getMarks(), marks.getMarksId());
						i++;
					}else {
						break;
					}
				}
			}
			else if(examName.equalsIgnoreCase("midTestOne")) {
				for (UpdateMarksRequest marks : listOfMarksToBeUpdated) {
					if(util.getNumberOfDaysBetweenCreatedDateAndCurrentDate(marks.getDate()) <= 1) {
						marksRepository.updateMidTestOneMarks(marks.getMarks(), marks.getMarksId());
						i++;
					}else {
						break;
					}
				}
			}
			else if(examName.equalsIgnoreCase("unitTestTwo")) {
				for (UpdateMarksRequest marks : listOfMarksToBeUpdated) {
					if(util.getNumberOfDaysBetweenCreatedDateAndCurrentDate(marks.getDate()) <= 1) {
						marksRepository.updateUnitTestTwoMarks(marks.getMarks(), marks.getMarksId());
						i++;
					}else {
						break;
					}
				}
			}
			else if(examName.equalsIgnoreCase("midTestTwo")) {
				for (UpdateMarksRequest marks : listOfMarksToBeUpdated) {
					if(util.getNumberOfDaysBetweenCreatedDateAndCurrentDate(marks.getDate()) <= 1) {
						marksRepository.updateMidTestTwoMarks(marks.getMarks(), marks.getMarksId());
						i++;
					}else {
						break;
					}
				}
			}
			else if(examName.equalsIgnoreCase("assignmentOne")) {
				for (UpdateMarksRequest marks : listOfMarksToBeUpdated) {
					if(util.getNumberOfDaysBetweenCreatedDateAndCurrentDate(marks.getDate()) <= 1) {
						marksRepository.updateAssignmentOneMarks(marks.getMarks(), marks.getMarksId());
						i++;
					}else {
						break;
					}
				}
			}
			else if(examName.equalsIgnoreCase("assignmentTwo")) {
				for (UpdateMarksRequest marks : listOfMarksToBeUpdated) {
					if(util.getNumberOfDaysBetweenCreatedDateAndCurrentDate(marks.getDate()) <= 1) {
						marksRepository.updateAssignmentTwoMarks(marks.getMarks(), marks.getMarksId());
						i++;
					}else {
						break;
					}
				}
			}
			else if(examName.equalsIgnoreCase("endSemester")) {
				for (UpdateMarksRequest marks : listOfMarksToBeUpdated) {
					if(util.getNumberOfDaysBetweenCreatedDateAndCurrentDate(marks.getDate()) <= 1) {
						marksRepository.updateEndSemesterMarks(marks.getMarks(), marks.getMarksId());
						i++;
					}else {
						break;
					}
				}
			}
			else {
				i = 0;
			}
			
			if(i != 0) {
				return new MarksOperationResponse (listOfStudentsRegisteredUnderSpecificTeacherAndCourse(slot, courseCode, teacherId), "Created successfully.");
			}
			else {
				return new MarksOperationResponse (null, "Teacher cannot edit.");
			}
		}
		else {
			return new MarksOperationResponse (null, "Something went wrong.");
		}
	}

}
