package com.ssafy.springtest.model.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.springtest.model.dto.MemberDto;

@Mapper
public interface MemberDao {
    MemberDto findMember(String userId) throws SQLException;

    Integer registerMember(MemberDto memberDto) throws SQLException;

    Integer modifyMember(MemberDto memberDto) throws SQLException;

    void deleteMember(String userId) throws SQLException;
    
}
