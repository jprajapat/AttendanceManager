package com.attendance.manager.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attendance.manager.entities.Employee;
import com.attendance.manager.servicesImpl.EmpServiceImpl;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmpServiceImpl empServiceImpl;

	@PostMapping("/saveEmployee")
	public Employee saveEmployee(@RequestBody Employee employee) {
		
		logger.info("saveEmployee EmployeeController method running");
		return empServiceImpl.saveEmplyee(employee);
	}
	
	@GetMapping("/getAllEmployee")
	public List<Employee> getAllEmployee() {
		logger.info("getAllEmployee EmployeeController method running");
		return empServiceImpl.getAllEmployee();
	}
	
	@PutMapping("/updateEmployee/{id}")
	public Employee updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
		logger.info("updateEmployee EmployeeController method running  ", id);
			return empServiceImpl.updateEmployee(id, employee);
		}
	
	@DeleteMapping("/deleteEmployee/{id}")
	public void deleteEmployee(@PathVariable("id") int id) {
		logger.info("deleteEmployee EmployeeController method running  ", id);
		empServiceImpl.deleteEmployee(id);
	}
	
	@GetMapping("/getEmployeeByName/{name}")
	public List<Employee> getEmployeeByName(@PathVariable("name") String name){
		logger.info("getEmployeeByName EmployeeController method running  ", name);
		return empServiceImpl.findByEmployeeName(name);
	}
	
	@GetMapping("/getEmployeeStartingName/{name}")
	public List<Employee> getEmployeeNameStartingWith(@PathVariable("name") String startingName){
		logger.info("getEmployeeStartingName EmployeeController method running  ", startingName);
		return empServiceImpl.findByEmployeeNameStartingWith(startingName);
	}

}
