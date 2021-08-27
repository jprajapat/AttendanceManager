package com.attendance.manager.testservices;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.attendance.manager.entities.Attendance;
import com.attendance.manager.repositories.AttendanceRepo;
import com.attendance.manager.servicesImpl.AttServiceImpl;

@SpringBootTest
public class TestAttendenceService {
	
	@InjectMocks
	private AttServiceImpl attServiceImpl;
	
	@Mock
	private AttendanceRepo attendenceRepo;
	
	@Test
	public void testGetAttendence() {
		
		Attendance attendence = new Attendance(101, "10/02/2021", "yes", null);
		Attendance attendence1 = new Attendance(102, "10/02/2021", "no", null);
		
		List<Attendance> attList = new ArrayList<Attendance>();
		attList.add(attendence);
		attList.add(attendence1);
		
		when(attendenceRepo.findAll()).thenReturn(attList);
		assertEquals(2,attServiceImpl.getAllAttendance().size());
	}
	
	@Test
	public void testSaveAttendence() {
		
		Attendance attendence = new Attendance(101, "10/02/2021", "yes", null);
		
		when(attendenceRepo.save(attendence)).thenReturn(attendence);
		
		assertEquals(attendence, attServiceImpl.saveAttendance(attendence));
	}
	
	@Test
	public void testUpdateAttendence() {
		
		Attendance attendence = new Attendance(101, "10/02/2021", "yes", null);
		
		when(attendenceRepo.save(attendence)).thenReturn(attendence);
		
		assertThat(attServiceImpl.updateAttendance(101, attendence));
	}
	
	@Test
	public void testDeleteAttendance() {
		
		Attendance attendence = new Attendance(101, "10/02/2021", "yes", null);
		attServiceImpl.deleteAttendance(attendence.getId());
		verify(attendenceRepo,times(1)).deleteById(attendence.getId());
	}

}
