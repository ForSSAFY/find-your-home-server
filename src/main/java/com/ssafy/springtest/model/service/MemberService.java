package com.ssafy.springtest.model.service;

import java.sql.SQLException;

import com.ssafy.springtest.model.dto.MemberDto;

public interface MemberService {

    MemberDto login(String userId, byte[] userPassword) throws Exception;

    MemberDto getMemberInfo(String userId) throws Exception;

    String findUserPw(String userId, String userName, String phone) throws Exception;

    boolean register(MemberDto memberDto) throws Exception;

    boolean modifyMember(MemberDto memberDto) throws Exception;

    boolean changeUserPw(String userId, byte[] oldPassword, byte[] pw) throws Exception;

    boolean delete(String userId) throws Exception;
    
    String Byte_to_Hexa(byte[] temp);
        
    String getSalt() throws Exception;
    
    String Hashing(byte[] userPassword, String salt) throws Exception;

}
