package com.pet.web.dao;

import com.pet.web.vo.MemberVO;

public interface MemberMapper {

	int join(MemberVO joinMember);

	MemberVO login(MemberVO loginMember);

	int idCheck(String memberId);

	MemberVO selectId(String memberId);

	int updateMember(MemberVO updateMember);

}
