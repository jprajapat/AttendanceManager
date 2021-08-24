package com.attendance.manager.testservices;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.attendance.manager.entities.Employee;
import com.attendance.manager.repositories.EmployeeRepo;
import com.attendance.manager.servicesImpl.EmpServiceImpl;

@SpringBootTest
public class TestEmployeServices {

	@InjectMocks
	private EmpServiceImpl empServiceImpl;

	@Mock
	private EmployeeRepo employeeRepo;

	@Test
	public void testGetEmployee() {
		Employee employee = new Employee();

		employee.setId(101);
		employee.setEmployeeName("Yes");
		employee.setEmail("yes@gmail.com");
		employee.setContactNumber("123456789");

		when(employeeRepo.findAll()).thenReturn(Stream.of(employee).collect(Collectors.toList()));
		assertEquals(1, empServiceImpl.getAllEmployee().size());

	}

	@Test
	public void testSaveEmployee() {
		Employee employee = new Employee();

		employee.setId(101);
		employee.setEmployeeName("Yes");
		employee.setEmail("yes@gmail.com");
		employee.setContactNumber("123456789");
		when(employeeRepo.save(employee)).thenReturn(employee);

		assertEquals(employee, empServiceImpl.saveEmplyee(employee));
	}

	@Test
	public void testGetEmployeeName() {
		Employee employee = new Employee();

		employee.setId(101);
		employee.setEmployeeName("Yes");
		employee.setEmail("yes@gmail.com");
		employee.setContactNumber("123456789");

		when(employeeRepo.findByEmployeeName("Yes")).thenReturn(Stream.of(employee).collect(Collectors.toList()));
		assertThat(empServiceImpl.findByEmployeeName("Yes")).isNotNull();

	} 

	@Test
	public void testGetEmployeeStartingName() {
		Employee employee = new Employee();

		employee.setId(101);
		employee.setEmployeeName("Yes");
		employee.setEmail("yes@gmail.com");
		employee.setContactNumber("123456789");

		when(employeeRepo.findByEmployeeNameStartingWith("y"))
				.thenReturn(Stream.of(employee).collect(Collectors.toList()));

		assertThat(empServiceImpl.findByEmployeeNameStartingWith("y")).isNotNull();

	}

	@Test
	public void testUpdateEmployee() {
		Employee employee = new Employee();

		employee.setId(101);
		employee.setEmployeeName("Yes");
		employee.setEmail("yes@gmail.com");
		employee.setContactNumber("123456789");

		when(employeeRepo.save(employee)).thenReturn(employee);

		assertThat(empServiceImpl.updateEmployee(101, employee));
	}

	@Test
	public void testDeleteEmployee() {
		Employee employee = new Employee();

		employee.setId(101);
		employee.setEmployeeName("Yes");
		employee.setEmail("yes@gmail.com");
		employee.setContactNumber("123456789");

		employeeRepo.deleteById(employee.getId());
		verify(employeeRepo, times(1)).deleteById(employee.getId());
	}

}
