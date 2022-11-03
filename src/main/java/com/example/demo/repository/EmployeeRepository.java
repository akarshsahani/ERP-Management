package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findByOfficialEmail(String email);
	
	Employee findByPersonalEmail(String email);
	
//	@Modifying
//	@Transactional
//	@Query(value = "UPDATE student SET registration_number = ?1 WHERE email = ?2 ",  nativeQuery = true)
//	public void updateRegistrationNumber(String registrationNumber, String email);
	
	@Query(value = "SELECT employee_id FROM employee WHERE personal_email = ?1", nativeQuery = true)
	public Long returnEmployeeId(String email);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE employee SET password = ?1 WHERE employee_id = ?2 ",  nativeQuery = true)
	public void updatePassword(String password, Long id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE employee SET official_email = ?1 WHERE employee_id = ?2 ",  nativeQuery = true)
	public void updateOfficialEmail(String officialEmail, Long id);

}
