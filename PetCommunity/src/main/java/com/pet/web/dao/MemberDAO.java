package com.pet.web.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pet.web.vo.MemberVO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSession session;

	public MemberVO login(MemberVO loginMember) {
		MemberVO result = null;
		MemberMapper mapper = null;
		try {
			mapper = session.getMapper(MemberMapper.class);
			result = mapper.login(loginMember);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int idCheck(String memberId) {
		int result = 0;
		MemberMapper mapper = null;
		try {
			mapper = session.getMapper(MemberMapper.class);
			result = mapper.idCheck(memberId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public MemberVO selectId(String memberId) {
		MemberVO result = null;
		MemberMapper mapper = null;
		try {
			mapper = session.getMapper(MemberMapper.class);
			result = mapper.selectId(memberId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateMember(MemberVO updateMember) {
		System.out.println(updateMember);
		int result = 0;
		MemberMapper mapper = null;
		try {
			mapper = session.getMapper(MemberMapper.class);
			result = mapper.updateMember(updateMember);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int join(MemberVO joinMember) {
		int result = 0;
		MemberMapper mapper = null;
		try {
			mapper = session.getMapper(MemberMapper.class);
			result = mapper.join(joinMember);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
}