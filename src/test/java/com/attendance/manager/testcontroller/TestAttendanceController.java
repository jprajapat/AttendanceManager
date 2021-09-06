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
import org.springframework.test.web.servlet.MockMvc;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

import com.attendance.manager.controllers.AttendanceController;
import com.attendance.manager.entities.Attendance;
import com.attendance.manager.servicesImpl.AttServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = AttendanceController.class)
@WithMockUser(username = "jitendra",password = "123",roles = "ADMIN")
//@WithMockUser(username = "employee",password = "123",roles = "NORMAL")
public class TestAttendanceController {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private AttServiceImpl attServiceImpl;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() {

		objectMapper.registerModule(new ProblemModule());
		objectMapper.registerModule(new ConstraintViolationProblemModule());

	}

	@Test
	public void testGetAttendance() throws Exception {
		Attendance attendance = new Attendance(101, "10/02/2021", "yes", null);
		Attendance attendance1 = new Attendance(102, "10/02/2021", "no", null);

		List<Attendance> attList = new ArrayList<Attendance>();

		attList.add(attendance);
		attList.add(attendance1);

		when(attServiceImpl.getAllAttendance()).thenReturn(attList);

		mockMvc.perform(get("/attendance/getAttendance")).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(attList.size())));
	}

	@Test
	public void testSaveAttendance() throws Exception {

		Attendance attendance = new Attendance(101, "11/02/2021", "yes", null);

		when(attServiceImpl.saveAttendance(attendance)).thenReturn(attendance);

		mockMvc.perform(post("/attendance/saveAttendance").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(attendance))).andExpect(status().isOk());
	}

	@Test
	public void testUpdateAttendance() throws Exception {

		Attendance attendance = new Attendance(101, "11/02/2021", "yes", null);

		when(attServiceImpl.updateAttendance(101, attendance)).thenReturn(attendance);

		mockMvc.perform(put("/attendance/updateAttendance/{id}", attendance.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(attendance))).andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteAttendance() throws Exception {
		
		Attendance attendance = new Attendance(101, "11/02/2021", "yes", null);
		
		doNothing().when(attServiceImpl).deleteAttendance(attendance.getId());
		
		mockMvc.perform(delete("/attendance/deleteAttendance/{id}", attendance.getId())).andExpect(status().isOk());
	}

}
