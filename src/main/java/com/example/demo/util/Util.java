package com.example.demo.util;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class Util {

	public LocalDate stringToDate(String stringDate) {
		return LocalDate.parse(stringDate);
//		https://www.sololearn.com/Discuss/1858324/pls-help-me-write-a-java-program-that-will-accept-someone-date-of-birth-using-the-current-date-to
	}
	
	public String generateRegistrationNumber(Long id, String programme) {
//		String date = (String)LocalDate.now();
		String str = Long.toString(id);
//		String registrationNumber = null;
		int length = str.length();
		if(programme.equalsIgnoreCase("computer science and engineering")) {
			if(length==1) {
				return "18" + "UE" + "CS" + "000" + str;
			}else if(length == 2) {
				return "18" + "UE" + "CS" + "00" + str;
			}else if(length == 3) {
				return "18" + "UE" + "CS" + "0" + str;
			}else {
				return "18" + "UE" + "CS" + str;
			}
		}else if(programme.equalsIgnoreCase("electronics and communication engineering")) {
			if(length==1) {
				return "18" + "UE" + "EC" + "000" + str;
			}else if(length == 2) {
				return "18" + "UE" + "EC" + "00" + str;
			}else if(length == 3) {
				return "18" + "UE" + "EC" + "0" + str;
			}else {
				return "18" + "UE" + "EC" + str;
			}
		}else if(programme.equalsIgnoreCase("Electrical and Electronics Engineering")) {
			if(length==1) {
				return "18" + "UE" + "EE" + "000" + str;
			}else if(length == 2) {
				return "18" + "UE" + "EE" + "00" + str;
			}else if(length == 3) {
				return "18" + "UE" + "EE" + "0" + str;
			}else {
				return "18" + "UE" + "EE" + str;
			}
		}else if(programme.equalsIgnoreCase("Mechanical Engineering")) {
			if(length==1) {
				return "18" + "UE" + "ME" + "000" + str;
			}else if(length == 2) {
				return "18" + "UE" + "ME" + "00" + str;
			}else if(length == 3) {
				return "18" + "UE" + "ME" + "0" + str;
			}else {
				return "18" + "UE" + "ME" + str;
			}
		}else if(programme.equalsIgnoreCase("Civil Engineering")) {
			if(length==1) {
				return "18" + "UE" + "CE" + "000" + str;
			}else if(length == 2) {
				return "18" + "UE" + "CE" + "00" + str;
			}else if(length == 3) {
				return "18" + "UE" + "CE" + "0" + str;
			}else {
				return "18" + "UE" + "CE" + str;
			}
		}else if(programme.equalsIgnoreCase("Information technology")) {
			if(length==1) {
				return "18" + "UE" + "IT" + "000" + str;
			}else if(length == 2) {
				return "18" + "UE" + "IT" + "00" + str;
			}else if(length == 3) {
				return "18" + "UE" + "IT" + "0" + str;
			}else {
				return "18" + "UE" + "IT" + str;
			}
		}else {
			return "Cannot create registration number";
		}
	}
}
