package com.ssafy.springtest.model.service;

import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.springtest.model.dao.DongCodeDao;
import com.ssafy.springtest.model.dao.HouseDealDao;
import com.ssafy.springtest.model.dto.HouseDealDto;
import com.ssafy.springtest.model.dto.HouseDealSimpleDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

	private final DongCodeDao dongCodeDao;
    private final HouseDealDao houseDealDao;

    @Override
    public List<String> getSidos() throws SQLException {
        return dongCodeDao.getSidos();
    }

    @Override
    public List<String> getGuguns(String sidoName) throws SQLException {
        return dongCodeDao.getGuguns(sidoName);
    }

    @Override
    public List<String> getDongs(String sidoName, String gugunName) throws SQLException {
        return dongCodeDao.getDongs(sidoName, gugunName);
    }

    @Override
    public List<Integer> getYears() {
        List<Integer> list = new ArrayList<>();
        int year = Year.now().getValue();
        for (int y = year; y > year - 20; y--) {
            list.add(y);
        }
        return list;
    }

    @Override
    public List<HouseDealDto> getHouseDeals(String sidoName, String gugunName, String dongName, int dealYear, int dealMonth) throws SQLException {
        List<HouseDealDto> list = new ArrayList<>();
        String dongCode = dongCodeDao.getDongCode(sidoName, gugunName, dongName);
        if (dongCode == null) return list;
        return houseDealDao.findAllByDongCode(new HouseDealSimpleDto(dongCode, dealYear, dealMonth));
    }
}
