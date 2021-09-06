package com.attendance.manager.testcontroller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

import com.attendance.manager.controllers.EmployeeController;
import com.attendance.manager.entities.Employee;
import com.attendance.manager.servicesImpl.EmpServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@WebMvcTest(controllers = EmployeeController.class)
//@WithMockUser(username = "employee",password = "123",roles = "NORMAL")
@WithMockUser(username = "jitendra",password = "123",roles = "ADMIN")
public class TestEmployeeController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmpServiceImpl empServiceImpl;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() {

		objectMapper.registerModule(new ProblemModule());
		objectMapper.registerModule(new ConstraintViolationProblemModule());

	}

	@Test
	public void testGetAllEmployee() throws Exception {

		Employee employee = new Employee();
		List<Employee> empList = new ArrayList<Employee>();

		employee.setId(101);
		employee.setEmployeeName("Ayush");
		employee.setEmail("ayush@gmail.com");
		employee.setContactNumber("1234567890");
		empList.add(employee);

		when(empServiceImpl.getAllEmployee()).thenReturn(empList);

		mockMvc.perform(get("/employee/getAllEmployee")).andExpect(status().isOk());
	}

	@Test
	public void testSaveEmployee() throws Exception {

		Employee emp = new Employee();

		emp.setId(101);
		emp.setEmployeeName("Ashish");
		emp.setEmail("ashish@gmail.com");
		emp.setContactNumber("1234567890");

		when(empServiceImpl.saveEmplyee(emp)).thenReturn(emp);

		mockMvc.perform(post("/employee/saveEmployee").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(emp))).andExpect(status().isOk());

	}

	@Test
	public void testGetEmployeeByName() throws Exception {

		Employee emp = new Employee();

		List<Employee> empList = new ArrayList<Employee>();

		emp.setId(101);
		emp.setEmployeeName("Ashish");
		emp.setEmail("ashish@gmail.com");
		emp.setContactNumber("1234567890");

		empList.add(emp);

		when(empServiceImpl.findByEmployeeName("Ashish")).thenReturn(empList);

		mockMvc.perform(get("/employee/getEmployeeByName/{name}", emp.getEmployeeName()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(empList.size())));
	}

	@Test
	
	public void testGetEmployeeByStartingName() throws Exception {

		Employee emp = new Employee();

		List<Employee> empList = new ArrayList<Employee>();

		emp.setId(101);
		emp.setEmployeeName("Piyush");
		emp.setEmail("piyush@gmail.com");
		emp.setContactNumber("1234567890");

		empList.add(emp);

		when(empServiceImpl.findByEmployeeNameStartingWith("p")).thenReturn(empList);

		mockMvc.perform(get("/employee/getEmployeeStartingName/{name}", "p"))
				.andExpect(status().isOk());
	}

	@Test
	public void testUpdateEmployee() throws Exception {

		Employee emp = new Employee();

		emp.setId(101);
		emp.setEmployeeName("Ashish");
		emp.setEmail("ashish@gmail.com");
		emp.setContactNumber("1234567890");
		
		when(empServiceImpl.updateEmployee(101, emp)).thenReturn(emp);

		mockMvc.perform(put("/employee/updateEmployee/{id}", emp.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(emp)))
				.andExpect(status().isOk());

	}

	@Test
	public void testDeleteEmployee() throws Exception {
		Employee emp = new Employee();

		emp.setId(101);
		emp.setEmployeeName("Piyush");
		emp.setEmail("piyush@gmail.com");
		emp.setContactNumber("1234567890");

		when(empServiceImpl.findByEmail(emp.getEmail())).thenReturn(emp);

		doNothing().when(empServiceImpl).deleteEmployee(emp.getId());

		mockMvc.perform(delete("/employee/deleteEmployee/{id}", emp.getId())).andExpect(status().isOk());
	}

}
