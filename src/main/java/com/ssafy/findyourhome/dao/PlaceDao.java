package com.ssafy.findyourhome.dao;

import com.ssafy.findyourhome.dto.place.*;
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
    @Select("select hi.apt_code AS id, hi.apartment_name AS name, hi.lat, hi.lng, hd.deal_amount_int AS price, hd.area AS area\n" +
            "from (\n" +
            "    select hi.*, MAX(hd.`no`) max_no\n" +
            "    from houseinfo hi\n" +
            "        join housedeal hd\n" +
            "        on hi.apt_code = hd.apt_code\n" +
            "WHERE lat BETWEEN #{minLat} AND #{maxLat}\n" +
            "AND lng BETWEEN #{minLng} AND #{maxLng}\n" +
            "    group by hi.apt_code\n" +
            ") hi\n" +
            "join housedeal hd\n" +
            "on hi.max_no = hd.`no`;")
    List<HouseInfoRes> findAllHouseByCoordinate(Double minLat, Double maxLat, Double minLng, Double maxLng) throws SQLException;

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

    @Select("SELECT apt_code AS id, hiar.lat, hiar.lng, apartment_name AS name, hd.deal_amount_int AS price, hd.area, CONCAT(sido_name, ' ', sigungu_name, ' ', eubmyundong_name) AS address, CONCAT(deal_year, '/', deal_month, '/', deal_day) AS date\n" +
            "FROM (SELECT apt_code, apartment_name, hi.lat, hi.lng, ar.sido_name, ar.sigungu_name, ar.eubmyundong_name FROM houseinfo hi JOIN area ar USING (li_code)) hiar\n" +
            "JOIN housedeal hd\n" +
            "USING (apt_code)\n" +
            "WHERE apt_code = #{id}\n" +
            "ORDER BY STR_TO_DATE(CONCAT(deal_year, '/', deal_month, '/', deal_day), '%Y/%m/%d') DESC\n" +
            "LIMIT 1;")
    HouseInfoRes findHouseById(String id);

    @Select("SELECT CONCAT(deal_year, '/', deal_month, '/', deal_day) AS date, deal_amount_int AS price, CAST(floor AS SIGNED) AS floor\n" +
            "FROM houseinfo\n" +
            "JOIN housedeal\n" +
            "USING (apt_code)\n" +
            "WHERE apt_code = #{id}\n" +
            "ORDER BY STR_TO_DATE(CONCAT(deal_year, '/', deal_month, '/', deal_day), '%Y/%m/%d') DESC;")
    List<HouseDealInfoSimpleDto> findAllDealByHouseId(String id);


    @Select("SELECT \n" +
            "\t#{type} AS type,\n" +
            "    store_name AS name, \n" +
            "    ROUND(ST_DISTANCE(\n" +
            "        (SELECT coordinate FROM houseinfo WHERE apt_code = #{id}), \n" +
            "        coordinate\n" +
            "    ) / 100) AS minutes\n" +
            "FROM storeinfo\n" +
            "WHERE business_area_sub_code = #{code}\n" +
            "ORDER BY minutes\n" +
            "LIMIT 1;")
    StoreDto findStoreNearByHouseId(String type, String code, String id);

    @Select("SELECT \n" +
            "\t#{type} AS type,\n" +
            "    CONCAT(name, '역') AS name, \n" +
            "    ROUND(ST_DISTANCE(\n" +
            "        (SELECT coordinate FROM houseinfo WHERE apt_code = #{id}), \n" +
            "        coordinate\n" +
            "    ) / 100) AS minutes\n" +
            "FROM subway\n" +
            "ORDER BY minutes\n" +
            "LIMIT 1;")
    StoreDto findSubwayNearByHouseId(String type, String id);

    @Select("SELECT \n" +
            "\t'park' AS type,\n" +
            "    name, \n" +
            "    CEIL(ST_DISTANCE(\n" +
            "        (SELECT coordinate FROM houseinfo WHERE apt_code = #{id}), \n" +
            "        coordinate\n" +
            "    ) / 50) AS minutes\n" +
            "FROM park\n" +
            "ORDER BY minutes\n" +
            "LIMIT 1;")
    StoreDto findParkNearByHouseId(String id);

    @Select("SELECT \n" +
            "\t'charger' AS type,\n" +
            "    name, \n" +
            "    CEIL(ST_DISTANCE(\n" +
            "        (SELECT coordinate FROM houseinfo WHERE apt_code = #{id}), \n" +
            "        coordinate\n" +
            "    ) / 400) AS minutes\n" +
            "FROM echarger\n" +
            "ORDER BY minutes\n" +
            "LIMIT 1;")
    StoreDto findEchargerNearByHouseId(String id);

    @Select("SELECT eubmyundong_code AS id, 'location' AS type, eubmyundong_name AS name, CONCAT(sido_name, ' ', sigungu_name) AS address, lat, lng\n" +
            "FROM area\n" +
            "WHERE li_name IS NULL AND eubmyundong_name LIKE #{keyword}")
    List<PlaceDto> searchLocationByKeyword(String keyword);

    @Select("SELECT code AS id, 'subway' AS type, name, lat, lng FROM subway WHERE name LIKE #{keyword}")
    List<PlaceDto> searchSubwayByKeyword(String keyword);

    @Select("SELECT hi.apt_code AS id, 'apt' AS type, apartment_name AS name, CONCAT(ar.sido_name, ' ', ar.sigungu_name, ' ', ar.eubmyundong_name) AS address, hi.lat, hi.lng\n" +
            "FROM houseinfo hi\n" +
            "JOIN area ar\n" +
            "USING (li_code)\n" +
            "WHERE apartment_name LIKE #{keyword}")
    List<PlaceDto> searchAptByKeyword(String keyword);

}
