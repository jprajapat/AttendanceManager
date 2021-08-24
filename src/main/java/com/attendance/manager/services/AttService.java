package com.attendance.manager.services;

import java.util.List;

import com.attendance.manager.entities.Attendance;

public interface AttService {
	
	public Attendance saveAttendance(Attendance attendence);
	public List<Attendance> getAllAttendance();
	public void deleteAttendance(int id);
	public Attendance updateAttendance(int id, Attendance attendence);

	

}
