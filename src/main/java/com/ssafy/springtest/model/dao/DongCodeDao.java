package com.ssafy.springtest.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DongCodeDao {
    List<String> getSidos() throws SQLException;

    List<String> getGuguns(String sidoName) throws SQLException;

    List<String> getDongs(String sidoName, String gugunName) throws SQLException;

    String getDongCode(String sidoName, String gugunName, String dongName) throws SQLException;
}
