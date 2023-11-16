package com.ssafy.findyourhome.dao.deal;

import com.ssafy.findyourhome.dto.deal.DealReq;
import com.ssafy.findyourhome.dto.deal.HouseDealInfoDto;
import com.ssafy.findyourhome.dto.deal.HouseInfoRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface HouseDealDao {

    // TODO: remove LIMIT 100
    @Select("SELECT * FROM housedeal hd JOIN houseinfo hi ON hd.aptCode = hi.aptCode WHERE hi.dongCode = #{dongCode} AND hd.dealYear = #{year} AND hd.dealMonth = #{month} ORDER BY hd.dealYear DESC, hd.dealMonth DESC, hd.dealDay DESC LIMIT 100")
    List<HouseDealInfoDto> findAllByDongCode(DealReq req) throws SQLException;

//    @Select("SELECT * FROM housedeal hd JOIN houseinfo hi ON hd.aptCode = hi.aptCode WHERE (CAST(hi.lat AS DOUBLE) BETWEEN #{minLat} AND #{maxLat}) AND (CAST(hi.lng AS DOUBLE) BETWEEN #{minLng} AND #{maxLng}) AND hd.dealYear = #{year} AND hd.dealMonth = #{month} ORDER BY hd.dealYear DESC, hd.dealMonth DESC, hd.dealDay DESC LIMIT 100")
//    List<HouseDealInfoDto> findAllByCoordinate(Double minLat, Double maxLat, Double minLng, Double maxLng) throws SQLException;

    @Select("SELECT * FROM houseinfo WHERE (CAST(lat AS DOUBLE) BETWEEN #{minLat} AND #{maxLat}) AND (CAST(lng AS DOUBLE) BETWEEN #{minLng} AND #{maxLng}) LIMIT 100")
    List<HouseInfoRes> findAllHouseByCoordinate(Double minLat, Double maxLat, Double minLng, Double maxLng) throws SQLException;
}
