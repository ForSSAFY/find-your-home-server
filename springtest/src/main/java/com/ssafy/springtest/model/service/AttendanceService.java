package com.ssafy.springtest.model.service;

import java.util.List;

import com.ssafy.springtest.model.dto.Attendance;

public interface AttendanceService {
	public int insert(Attendance attend);
	public List<Attendance> search(String userNumber);
	public Attendance getAttendanceByAno(String ano);
}
