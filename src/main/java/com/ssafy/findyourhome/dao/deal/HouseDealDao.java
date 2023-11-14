package com.ssafy.findyourhome.dao.deal;

import com.ssafy.findyourhome.dto.deal.HouseDealInfoDto;
import com.ssafy.findyourhome.dto.deal.HouseDealInfoSimpleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface HouseDealDao {

    @Select("SELECT * FROM housedeal hd JOIN houseinfo hi ON hd.aptCode = hi.aptCode WHERE hi.dongCode = #{dongCode} AND hd.dealYear = #{dealYear} AND hd.dealMonth = #{dealMonth} ORDER BY hd.dealYear DESC, hd.dealMonth DESC, hd.dealDay DESC LIMIT 100")
    List<HouseDealInfoDto> findAllByDongCode(HouseDealInfoSimpleDto houseDealInfoSimpleDto) throws SQLException;
}
