package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findByEmail(String email);
	
//	@Modifying
//	@Transactional
//	@Query(value = "UPDATE student SET registration_number = ?1 WHERE email = ?2 ",  nativeQuery = true)
//	public void updateRegistrationNumber(String registrationNumber, String email);
	
	@Query(value = "SELECT employee_id FROM employee WHERE email = ?1", nativeQuery = true)
	public Long returnEmployeeId(String email);

}
