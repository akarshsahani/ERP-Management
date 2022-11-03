package com.example.demo.service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.LogInRequest;
import com.example.demo.dto.request.TeacherApplicantRequest;
import com.example.demo.dto.response.TeacherApplicantLoginResponse;
import com.example.demo.model.Role;
import com.example.demo.model.TeacherApplicant;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.TeacherApplicantRepository;
import com.example.demo.util.Util;

@Service
public class TeacherApplicantService {

	@Autowired
	private TeacherApplicantRepository teacherApplicantRepository;
	
	@Autowired
	private Util util;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private RoleRepository roleRepository;
	TeacherApplicant teacherApplicant = new TeacherApplicant();
	public String newTeacherApplicant (TeacherApplicantRequest teacherApplicantRequest) throws MessagingException {
		
		
		Role role = roleRepository.findByRoleName("APPLICANT");
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		
		teacherApplicant = TeacherApplicant.builder()
										   .firstName(teacherApplicantRequest.getFirstName())
										   .middleName(teacherApplicantRequest.getMiddleName())
										   .lastName(teacherApplicantRequest.getLastName())
										   .personalEmail(teacherApplicantRequest.getEmail())
										   .password(passwordEncoder.encode(teacherApplicantRequest.getPassword()))
										   .dateOfBirth(util.stringToDate(teacherApplicantRequest.getDateOfBirth()))
										   .phoneNumber(teacherApplicantRequest.getPhoneNumber())
										   .permanentAddress(teacherApplicantRequest.getPermanentAddress())
										   .currentAddress(teacherApplicantRequest.getCurrentAddress())
										   .tenthPercentage(teacherApplicantRequest.getTenthPercentage())
										   .tenthSchoolName(teacherApplicantRequest.getTenthSchoolName())
										   .tenthSchoolAddress(teacherApplicantRequest.getTenthSchoolAddress())
										   .tenthYearPassed(teacherApplicantRequest.getTenthYearPassed())
										   .twelvethPercentage(teacherApplicantRequest.getTwelvethPercentage())
										   .twelvethSchoolName(teacherApplicantRequest.getTwelvethSchoolName())
										   .twelvethSchoolAddress(teacherApplicantRequest.getTwelvethSchoolAddress())
										   .twelvethYearPassed(teacherApplicantRequest.getTwelvethYearPassed())
										   .bachelorPercentage(teacherApplicantRequest.getBachelorPercentage())
										   .bachelorSchoolName(teacherApplicantRequest.getBachelorSchoolName())
										   .bachelorSchoolAddress(teacherApplicantRequest.getBachelorSchoolAddress())
										   .bachelorBranch(teacherApplicantRequest.getBachelorBranch())
										   .bachelorProgramme(teacherApplicantRequest.getBachelorProgramme())
										   .bachelorYearPassed(teacherApplicantRequest.getBachelorYearPassed())
										   .mastersPercentage(teacherApplicantRequest.getMastersPercentage())
										   .mastersSchoolName(teacherApplicantRequest.getMastersSchoolName())
										   .mastersSchoolAddress(teacherApplicantRequest.getMastersSchoolAddress())
										   .mastersBranch(teacherApplicantRequest.getMastersBranch())
										   .mastersProgramme(teacherApplicantRequest.getMastersProgramme())
										   .mastersYearPassed(teacherApplicantRequest.getMastersYearPassed())
										   .appliedDate(LocalDate.now())
										   .category("teacher")
										   .currentStatus("applicant")
										   .role(roles)
										   .designation(teacherApplicantRequest.getDesignation())
//										   .otherQualifications(teacherApplicantRequest.getOtherQualifications())
										   .build();
		teacherApplicantRepository.save(teacherApplicant);
		util.sendMailWithOutAttachment(teacherApplicantRequest.getEmail(), "Application Submitted. Please login to applicant portal to check status.", "Application Submitted.");
		return "Application Submitted. Please login to applicant portal to check status.";
	}
	
	public TeacherApplicantLoginResponse teacherApplicantLogin(LogInRequest logInRequest) throws Exception {
		TeacherApplicant teacherApplicant = teacherApplicantRepository.findByPersonalEmail(logInRequest.getEmail());
		if(teacherApplicant != null) {
			String jwtToken = jwtService.createJwtToken(logInRequest);
			if(teacherApplicant.getCurrentStatus().equalsIgnoreCase("employee")) {
				String message = "Application approved. Please Login to teacher portal.";
				return new TeacherApplicantLoginResponse(teacherApplicant, jwtToken, message);
			}
			else {
				String message = "Application Submitted Successfully. Please wait until approved.";
				return new TeacherApplicantLoginResponse(teacherApplicant, jwtToken, message);
			}
		}else {
			return new TeacherApplicantLoginResponse(teacherApplicant, null, "Email not registered.");
		}
	}
	
	public TeacherApplicant teacherApplicantDetail(Principal principal) {
		return teacherApplicantRepository.findByPersonalEmail(principal.getName());
	}
}
