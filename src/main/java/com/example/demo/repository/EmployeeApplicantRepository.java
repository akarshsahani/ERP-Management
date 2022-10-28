package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.EmployeeApplicant;

@Repository
public interface EmployeeApplicantRepository extends JpaRepository<EmployeeApplicant, Long>{

	EmployeeApplicant findByEmail(String username);
	

	@Modifying
	@Transactional
	@Query(value = "UPDATE employee_applicant SET current_status = ?1 WHERE employee_applicant_id = ?2 ",  nativeQuery = true)
	public void updateStatuOfEmployeeApplicant(String currentStatus, Long id);


	EmployeeApplicant findByEmployeeApplicantId(Long id);

}
