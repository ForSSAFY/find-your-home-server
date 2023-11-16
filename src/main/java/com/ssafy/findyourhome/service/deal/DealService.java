package com.ssafy.findyourhome.service.deal;

import com.ssafy.findyourhome.dao.deal.DongCodeDao;
import com.ssafy.findyourhome.dao.deal.HouseDealDao;
import com.ssafy.findyourhome.dto.deal.DealReq;
import com.ssafy.findyourhome.dto.deal.HouseDealInfoDto;
import com.ssafy.findyourhome.dto.deal.HouseInfoRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
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

    public List<HouseDealInfoDto> getHouseDeals(DealReq req) throws SQLException {

        if (req.getYear() == null) {
            req.setYear(LocalDate.now().getYear());
        }
        if (req.getMonth() == null) {
            req.setMonth(LocalDate.now().getMonth().getValue());
        }
        log.info("getHouseDeals");
        List<HouseDealInfoDto> list = new ArrayList<>();
        String dongCode = dongCodeDao.getDongCode(req.getSido(), req.getGugun(), req.getDong());
        if (dongCode != null) {
            log.info("dongCode exists");
            req.setDongCode(dongCode);
            list = houseDealDao.findAllByDongCode(req);
        }

        return list;
    }

    public List<HouseInfoRes> getHouses(Double minLat, Double maxLat, Double minLng, Double maxLng) throws SQLException {
        return houseDealDao.findAllHouseByCoordinate(minLat, maxLat, minLng, maxLng);
    }
}
