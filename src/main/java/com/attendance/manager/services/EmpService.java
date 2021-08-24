package com.attendance.manager.services;

import java.util.List;

import com.attendance.manager.entities.Employee;

public interface EmpService {
	
	public Employee saveEmplyee(Employee employee);
	public List<Employee> getAllEmployee();
	public void deleteEmployee(int id);
	public Employee updateEmployee(int id, Employee employee);
	public List<Employee> findByEmployeeName(String employeeName);
	public List<Employee> findByEmployeeNameStartingWith(String startingName);
	public Employee findByEmail(String email);

}
