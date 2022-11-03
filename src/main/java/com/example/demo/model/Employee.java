package com.example.demo.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;
	
	private String firstName;
	private String lastName;
	private String middleName;
	private LocalDate dateOfBirth;
	private String phoneNumber;
	private String permanentAddress;
	private String currentAddress;
	
	@Column(unique = true)
	protected String personalEmail;
	
	@Column(unique = true)
	private String officialEmail;
	
	private LocalDate joiningDate;
	private LocalDate appliedDate;
	private String password;
//	private Set<Education> qualification;
	private String category;
	private String currentStatus;
	private String designation;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "employee_role",
	joinColumns = {
			@JoinColumn(name = "employeeId")
	},
	inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID")
	})
	private Set<Role> role;

}
