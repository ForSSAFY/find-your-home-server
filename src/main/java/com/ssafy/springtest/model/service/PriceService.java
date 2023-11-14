package com.ssafy.springtest.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.springtest.model.dto.HouseDealDto;

public interface PriceService {
    List<String> getSidos() throws SQLException;

    List<String> getGuguns(String sidoName) throws SQLException;

    List<String> getDongs(String sidoName, String gugunName) throws SQLException;

    List<Integer> getYears();

    List<HouseDealDto> getHouseDeals(String sidoName, String gugunName, String dongName, int dealYear, int dealMonth) throws SQLException;
}
