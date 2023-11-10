package com.ssafy.springtest.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.springtest.model.dao.AttendanceDao;
import com.ssafy.springtest.model.dto.Attendance;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

	private final AttendanceDao dao;
	
	@Override
	public int insert(Attendance attend) {
		log.debug("attend가 넘어왔나요??");
		return dao.insert(attend);
	}

	@Override
	public List<Attendance> search(String userNumber) {
		return dao.search(userNumber);
	}

	@Override
	public Attendance getAttendanceByAno(String ano) {
		return dao.getAttendanceByAno(ano);
	}

}
