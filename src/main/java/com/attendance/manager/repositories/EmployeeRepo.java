package com.attendance.manager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.manager.entities.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	
	public List<Employee> findByEmployeeName(String employeeName);
	public List<Employee> findByEmployeeNameStartingWith(String startingName);
	public Employee findByEmail(String email);

}
