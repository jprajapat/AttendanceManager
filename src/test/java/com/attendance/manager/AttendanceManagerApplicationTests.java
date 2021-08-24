 package com.attendance.manager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.attendance.manager.entities.Employee;
import com.attendance.manager.repositories.EmployeeRepo;

@SpringBootTest
class AttendanceManagerApplicationTests {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	
	@Test
	public void testSaveEmployee() {
		Employee emp = new Employee();
		emp.setEmployeeName("Yogesh");
		emp.setEmail("yogesh@gmail.com");
		emp.setContactNumber("4568792012");
		employeeRepo.save(emp);
		assertNotNull(employeeRepo.findByEmployeeName("yogesh"));
	}

	@Test
	public void testGetEmplyee(){
		List<Employee> employee = employeeRepo.findAll();
		assertThat(employee).size().isGreaterThan(0);
	}
	
	@Test
	public void testGetEmployeeByName() {
		List<Employee> empName = employeeRepo.findByEmployeeName("yogesh");
		
		for(Employee emp:empName) {
		assertEquals("yogesh",emp.getEmployeeName());
		}
	}
	
	@Test
	public void testUpdateEmployee() {
		Employee employee = employeeRepo.findById(1).get();
		employee.setContactNumber("1521687907");
		employeeRepo.save(employee);
		assertNotEquals(0,employeeRepo.findById(1).get().getContactNumber());
	}
	
	@Test
	public void testDeleteEmployee() {
		Employee email = employeeRepo.findByEmail("yogesh@gmail.com");
		System.out.println(email.getEmail()+"  "+email.getId());
		
		employeeRepo.deleteById(email.getId());
		assertThat(employeeRepo.existsById(email.getId())).isFalse();
	}

}
