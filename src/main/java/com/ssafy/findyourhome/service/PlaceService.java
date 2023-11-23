package com.ssafy.findyourhome.service;

import com.ssafy.findyourhome.dao.PlaceDao;
import com.ssafy.findyourhome.dto.place.*;
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
public class PlaceService {

    private final PlaceDao placeDao;

    public List<String> getSidos() throws SQLException {
        return placeDao.getSidos();
    }

    public List<String> getGuguns(String sidoName) throws SQLException {
        return placeDao.getGuguns(sidoName);
    }

    public List<String> getDongs(String sidoName, String gugunName) throws SQLException {
        return placeDao.getDongs(sidoName, gugunName);
    }

    public List<Integer> getYears() {
        List<Integer> list = new ArrayList<>();
        int year = Year.now().getValue();
        for (int y = year; y > year - 20; y--) {
            list.add(y);
        }
        return list;
    }

    public List<HouseDealInfoDto> getHouseDeals(PlaceReq req) throws SQLException {

        if (req.getYear() == null) {
            req.setYear(LocalDate.now().getYear());
        }
        if (req.getMonth() == null) {
            req.setMonth(LocalDate.now().getMonth().getValue());
        }
        log.info("getHouseDeals");
        List<HouseDealInfoDto> list = new ArrayList<>();
        String dongCode = placeDao.getDongCode(req.getSido(), req.getGugun(), req.getDong());
        if (dongCode != null) {
            log.info("dongCode exists");
            req.setDongCode(dongCode);
            list = placeDao.findAllByDongCode(req);
        }

        return list;
    }

    public HouseDetailRes getHouse(String id) {

        HouseInfoRes house = placeDao.findHouseById(id);
        List<StoreDto> convs = new ArrayList<>();
        convs.add(placeDao.findSubwayNearByHouseId("subway", id));
        convs.add(placeDao.findStoreNearByHouseId("store", "G20405", id));
        convs.add(placeDao.findParkNearByHouseId(id));
        convs.add(placeDao.findEchargerNearByHouseId(id));
        for (StoreDto conv :
                convs) {
            log.info("getHouse Nearby {}", conv.getName());
        }
// placeDao.findStoreNearByHouseId("store", "G20405", id);

        return HouseDetailRes.builder()
                .id(house.getId())
                .name(house.getName())
                .address(house.getAddress())
                .date(house.getDate())
                .lat(house.getLat())
                .lng(house.getLng())
                .price(house.getPrice())
                .area(house.getArea())
                .nearby(convs)
                .deals(placeDao.findAllDealByHouseId(id))
                .build();
    }

    public List<HouseInfoRes> getHouses(Double minLat, Double maxLat, Double minLng, Double maxLng) throws SQLException {
        return placeDao.findAllHouseByCoordinate(minLat, maxLat, minLng, maxLng);
    }

    public List<SidogunInfoRes> getSidogunInfos(Double minLat, Double maxLat, Double minLng, Double maxLng, Integer level) throws SQLException {

        List<SidogunInfoRes> result;
        if (level <= 6) {
            result = placeDao.countHouseDongByCoordinate(minLat, maxLat, minLng, maxLng);
            log.info("countHouseDongByCoordinate {}", result.size());
        } else if (level <= 10) {
            log.info("countHouseGunguByCoordinate");
            result = placeDao.countHouseGunguByCoordinate(minLat, maxLat, minLng, maxLng);
        } else {
            log.info("countHouseSidoByCoordinate");
            result = placeDao.countHouseSidoByCoordinate(minLat, maxLat, minLng, maxLng);
        }
        return result;
    }


    public List<PlaceDto> getPlaces(String keyword) {
        List<PlaceDto> response = new ArrayList<>();
        List<PlaceDto> result;
        result = placeDao.searchLocationByKeyword(getWildcard(keyword));
        for (PlaceDto dto : result) {
            response.add(dto);
        }
        result = placeDao.searchSubwayByKeyword(getWildcard(keyword));
        for (PlaceDto dto : result) {
            dto.setName(dto.getName());
            response.add(dto);
        }
        result = placeDao.searchAptByKeyword(getWildcard(keyword));
        for (PlaceDto dto : result) {
            response.add(dto);
        }
        return response;
    }

    private String getWildcard(String str) {
        return "%" + str + "%";
    }
}
