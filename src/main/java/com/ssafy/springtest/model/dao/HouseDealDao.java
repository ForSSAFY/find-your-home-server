package com.ssafy.springtest.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.springtest.model.dto.HouseDealDto;
import com.ssafy.springtest.model.dto.HouseDealSimpleDto;

@Mapper
public interface HouseDealDao {

    List<HouseDealDto> findAllByDongCode(HouseDealSimpleDto houseDealSimpleDto) throws SQLException;
}
