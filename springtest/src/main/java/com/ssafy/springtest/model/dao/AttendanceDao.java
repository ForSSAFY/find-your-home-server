package com.ssafy.springtest.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.springtest.model.dto.Attendance;

@Mapper
public interface AttendanceDao {
	public int insert(Attendance attend);
	public List<Attendance> search(String userNumber);
	public Attendance getAttendanceByAno(String ano);
}


