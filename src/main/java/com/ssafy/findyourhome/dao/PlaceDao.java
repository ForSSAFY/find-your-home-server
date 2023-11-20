package com.ssafy.findyourhome.dao;

import com.ssafy.findyourhome.dto.place.PlaceReq;
import com.ssafy.findyourhome.dto.place.HouseDealInfoDto;
import com.ssafy.findyourhome.dto.place.HouseInfoRes;
import com.ssafy.findyourhome.dto.place.SidogunInfoRes;
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

//    @Select("SELECT aptCode as id, lat, lng, apartmentName as name FROM houseinfo WHERE (CAST(lat AS DOUBLE) BETWEEN #{minLat} AND #{maxLat}) AND (CAST(lng AS DOUBLE) BETWEEN #{minLng} AND #{maxLng})")
    @Select("SELECT hi.apt_code AS id, hi.apartment_name AS name, hi.lat AS lat, hi.lng AS lng, MAX(deal_amount_int) AS price, MAX(area) AS area\n" +
            "FROM housedeal \n" +
            "JOIN  (\n" +
            "SELECT * FROM houseinfo\n" +
            "WHERE lat BETWEEN #{minLat} AND #{maxLat}\n" +
            "AND lng BETWEEN #{minLng} AND #{maxLng}\n" +
            ") hi\n" +
            "USING (apt_code)\n" +
            "WHERE deal_year = #{dealYear} AND deal_month = #{dealMonth}\n" +
            "GROUP BY apt_code, lat, lng;")
    List<HouseInfoRes> findAllHouseByCoordinate(Double minLat, Double maxLat, Double minLng, Double maxLng, int dealYear, int dealMonth) throws SQLException;

    @Select("SELECT eubmyundong_code AS id, eubmyundong_name AS name, lat, lng, cnt\n" +
            "FROM (\n" +
            "SELECT * FROM area\n" +
            "WHERE eubmyundong_name IS NOT NULL\n" +
            ") sgg\n" +
            "JOIN (\n" +
            "SELECT SUBSTR(li_code, 1, 8) AS eubmyundong_code, COUNT(*) AS cnt\n" +
            "FROM houseinfo\n" +
            "WHERE lat BETWEEN #{minLat} AND #{maxLat}\n" +
            "AND lng BETWEEN #{minLng} AND #{maxLng}\n" +
            "GROUP BY SUBSTR(li_code, 1, 8)\n" +
            ") hi\n" +
            "USING (eubmyundong_code);")
    List<SidogunInfoRes> countHouseDongByCoordinate(Double minLat, Double maxLat, Double minLng, Double maxLng) throws SQLException;

    @Select("SELECT sigungu_code AS id, sigungu_name AS name, lat, lng, cnt\n" +
            "FROM (\n" +
            "SELECT * FROM area\n" +
            "WHERE sigungu_name IS NOT NULL\n" +
            "AND eubmyundong_name  IS NULL\n" +
            ") sgg\n" +
            "JOIN (\n" +
            "SELECT SUBSTR(li_code, 1, 5) AS sigungu_code, COUNT(*) AS cnt\n" +
            "FROM houseinfo\n" +
            "WHERE lat BETWEEN #{minLat} AND #{maxLat}\n" +
            "AND lng BETWEEN #{minLng} AND #{maxLng}\n" +
            "GROUP BY SUBSTR(li_code, 1, 5)\n" +
            ") hi\n" +
            "USING (sigungu_code);")
    List<SidogunInfoRes> countHouseGunguByCoordinate(Double minLat, Double maxLat, Double minLng, Double maxLng) throws SQLException;

    @Select("SELECT sido_code AS id, sido_name AS name, lat, lng, cnt\n" +
            "FROM (\n" +
            "SELECT * FROM area\n" +
            "WHERE sido_name IS NOT NULL\n" +
            "AND sigungu_name IS NULL\n" +
            ") sgg\n" +
            "JOIN (\n" +
            "SELECT SUBSTR(li_code, 1, 2) AS sido_code, COUNT(*) AS cnt\n" +
            "FROM houseinfo\n" +
            "WHERE lat BETWEEN #{minLat} AND #{maxLat}\n" +
            "AND lng BETWEEN #{minLng} AND #{maxLng}\n" +
            "GROUP BY SUBSTR(li_code, 1, 2)\n" +
            ") hi\n" +
            "USING (sido_code);")
    List<SidogunInfoRes> countHouseSidoByCoordinate(Double minLat, Double maxLat, Double minLng, Double maxLng) throws SQLException;
}
