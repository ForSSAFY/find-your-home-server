package com.ssafy.findyourhome.service.deal;

import com.ssafy.findyourhome.dao.deal.DongCodeDao;
import com.ssafy.findyourhome.dao.deal.HouseDealDao;
import com.ssafy.findyourhome.dto.deal.HouseDealInfoDto;
import com.ssafy.findyourhome.dto.deal.HouseDealInfoSimpleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DealService {

	private final DongCodeDao dongCodeDao;
    private final HouseDealDao houseDealDao;

    public List<String> getSidos() throws SQLException {
        return dongCodeDao.getSidos();
    }

    public List<String> getGuguns(String sidoName) throws SQLException {
        return dongCodeDao.getGuguns(sidoName);
    }

    public List<String> getDongs(String sidoName, String gugunName) throws SQLException {
        return dongCodeDao.getDongs(sidoName, gugunName);
    }

    public List<Integer> getYears() {
        List<Integer> list = new ArrayList<>();
        int year = Year.now().getValue();
        for (int y = year; y > year - 20; y--) {
            list.add(y);
        }
        return list;
    }

    public List<HouseDealInfoDto> getHouseDeals(String sidoName, String gugunName, String dongName, int dealYear, int dealMonth) throws SQLException {
        List<HouseDealInfoDto> list = new ArrayList<>();
        String dongCode = dongCodeDao.getDongCode(sidoName, gugunName, dongName);
        if (dongCode == null) return list;
        return houseDealDao.findAllByDongCode(new HouseDealInfoSimpleDto(dongCode, dealYear, dealMonth));
    }
}
