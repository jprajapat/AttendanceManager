package com.attendance.manager.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.manager.entities.Attendance;
import com.attendance.manager.exception.EmptyInputException;
import com.attendance.manager.exception.RecordNotFoundException;
import com.attendance.manager.repositories.AttendanceRepo;
import com.attendance.manager.services.AttService;

@Service
public class AttServiceImpl implements AttService{
	
	@Autowired
	private AttendanceRepo attendenceRepo;

	@Override
	public Attendance saveAttendance(Attendance attendence) {
		if(attendence.getIsPresent() == null) {
			throw new EmptyInputException();
		}
		
		return attendenceRepo.save(attendence);
		
	}

	@Override
	public List<Attendance> getAllAttendance() {
		return attendenceRepo.findAll();
	}

	@Override
	public void deleteAttendance(int id) {
				
		attendenceRepo.deleteById(id);
		
	}

	@Override
	public Attendance updateAttendance(int id, Attendance attendence) {
		
		Attendance att = attendenceRepo.findById(id).orElse(attendence);
		
		Attendance update;
		if(att.getId()==id) {
			att.setDate(attendence.getDate());
			att.setIsPresent(attendence.getIsPresent());
			update = attendenceRepo.save(att);
			
		}else {
			throw new RecordNotFoundException("id not present");
		}
		return update;
	}

}
