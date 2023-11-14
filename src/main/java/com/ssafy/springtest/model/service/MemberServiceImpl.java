package com.ssafy.springtest.model.service;

import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import org.springframework.stereotype.Service;

import com.ssafy.springtest.model.dao.MemberDao;
import com.ssafy.springtest.model.dto.MemberDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private static final int SALT_SIZE = 16;
	
    private final MemberDao memberDao;

    @Override
    public MemberDto login(String userId, byte[] userPassword) throws Exception {
        MemberDto member = memberDao.findMember(userId);
        if (member == null)
        	return null;
        String tempSalt = member.getSalt();
        String tempPw = Hashing(userPassword, tempSalt);
        if (member != null && tempPw.equals(member.getUserPassword())) {
        	return member;
        } else {
            return null;
        }
    }

    @Override
    public MemberDto getMemberInfo(String userId) throws Exception {
        return memberDao.findMember(userId);
    }

    @Override
    public String findUserPw(String userId, String userName, String phone) throws Exception {
        MemberDto member = memberDao.findMember(userId);
        if (member != null && member.getUserName().equals(userName) && member.getPhone().equals(phone)) {
            return member.getUserPassword();
        } else {
            return null;
        }
    }

    @Override
    public boolean register(MemberDto memberDto) throws Exception {
    	Integer i = memberDao.registerMember(memberDto);
    	if (i == null || i != 1 ) {
    		return false;
    	}
        return true;
    }

    @Override
    public boolean modifyMember(MemberDto memberDto) throws Exception {
    	Integer i = memberDao.modifyMember(memberDto);
    	if (i == null || i != 1 ) {
    		return false;
    	}
        return true;
    }

    @Override
    public boolean changeUserPw(String userId, byte[] oldPassword, byte[] newPassword) throws Exception {
        MemberDto member = memberDao.findMember(userId);
       
        
        String oldPw = Hashing(oldPassword, member.getSalt());
        if (member == null || !oldPw.equals(member.getUserPassword())) {
            return false;
        }
        System.out.println(oldPw);
        System.out.println(Hashing(newPassword, member.getSalt()));
        member.setUserPassword(Hashing(newPassword, member.getSalt()));
        memberDao.modifyMember(member);
        return true;
    }

    @Override
    public boolean delete(String userId) throws Exception {
        memberDao.deleteMember(userId);
        return true;
    }

	@Override
	//비밀번호를 hexa로 출력(16진수)
	public String Byte_to_Hexa(byte[] temp) {
		StringBuilder output = new StringBuilder();
		for (byte b : temp) {
			output.append(String.format("%02x", b));
		}
		return output.toString();
	}

	@Override
	//솔트 값 생성
	public String getSalt() throws Exception {
		SecureRandom rand = new SecureRandom();
		byte[] temp = new byte[SALT_SIZE];
		rand.nextBytes(temp);
		return Byte_to_Hexa(temp);
	}

	@Override
	//비밀번호 해싱
	public String Hashing(byte[] userPassword, String salt) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		//key - stretching
		for (int i = 0; i < 10000; i++) {
			String temp = Byte_to_Hexa(userPassword) + salt;	//패스워드와 Salt를 합쳐서 새로운 문자열 생성
			md.update(temp.getBytes());	//temp의 문자열을 해싱하여 md에 저장
			userPassword = md.digest();	//md 객체의 다이제스트를 얻어 password 갱신
		}
		
		return Byte_to_Hexa(userPassword);
	}

}
