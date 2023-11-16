package com.ssafy.findyourhome.dao;

import com.ssafy.findyourhome.dto.place.PlaceReq;
import com.ssafy.findyourhome.dto.place.HouseDealInfoDto;
import com.ssafy.findyourhome.dto.place.HouseInfoRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface PlaceDao {

    @Select("SELECT DISTINCT sidoName FROM dongcode WHERE sidoName IS NOT NULL")
    List<String> getSidos() throws SQLException;

    @Select("SELECT DISTINCT gugunName FROM dongcode WHERE sidoName = #{sidoName} AND gugunName IS NOT NULL")
    List<String> getGuguns(String sidoName) throws SQLException;

    @Select("SELECT DISTINCT dongName FROM dongcode WHERE sidoName = #{sidoName} AND gugunName = #{gugunName} AND dongName IS NOT NULL")
    List<String> getDongs(String sidoName, String gugunName) throws SQLException;

    @Select("SELECT dongCode FROM dongcode WHERE sidoName = #{sidoName} AND gugunName = #{gugunName} AND dongName = #{dongName}")
    String getDongCode(String sidoName, String gugunName, String dongName) throws SQLException;

    @Select("SELECT * FROM housedeal hd JOIN houseinfo hi ON hd.aptCode = hi.aptCode WHERE hi.dongCode = #{dongCode} AND hd.dealYear = #{year} AND hd.dealMonth = #{month} ORDER BY hd.dealYear DESC, hd.dealMonth DESC, hd.dealDay DESC")
    List<HouseDealInfoDto> findAllByDongCode(PlaceReq req) throws SQLException;

//    @Select("SELECT * FROM housedeal hd JOIN houseinfo hi ON hd.aptCode = hi.aptCode WHERE (CAST(hi.lat AS DOUBLE) BETWEEN #{minLat} AND #{maxLat}) AND (CAST(hi.lng AS DOUBLE) BETWEEN #{minLng} AND #{maxLng}) AND hd.dealYear = #{year} AND hd.dealMonth = #{month} ORDER BY hd.dealYear DESC, hd.dealMonth DESC, hd.dealDay DESC LIMIT 100")
//    List<HouseDealInfoDto> findAllByCoordinate(Double minLat, Double maxLat, Double minLng, Double maxLng) throws SQLException;

    @Select("SELECT aptCode as id, lat, lng, apartmentName as name FROM houseinfo WHERE (CAST(lat AS DOUBLE) BETWEEN #{minLat} AND #{maxLat}) AND (CAST(lng AS DOUBLE) BETWEEN #{minLng} AND #{maxLng})")
    List<HouseInfoRes> findAllHouseByCoordinate(Double minLat, Double maxLat, Double minLng, Double maxLng) throws SQLException;
}
