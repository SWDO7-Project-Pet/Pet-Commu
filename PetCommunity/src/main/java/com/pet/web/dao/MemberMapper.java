package com.pet.web.dao;

import com.pet.web.vo.MemberVO;

public interface MemberMapper {
	
	MemberVO login(MemberVO loginMember);

	int idCheck(String memberId);

	MemberVO selectId(String memberId);

	int updateMember(MemberVO updateMember);

	int join(MemberVO joinMember);
}