package com.attendance.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.manager.entities.Attendance;

public interface AttendanceRepo extends JpaRepository<Attendance, Integer> {

}
