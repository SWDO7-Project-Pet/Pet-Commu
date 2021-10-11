package com.pet.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.web.controller.MemberController;
import com.pet.web.dao.MemberDAO;
import com.pet.web.vo.MemberVO;

@Service
public class MemberService {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberDAO dao;

	public boolean join(String memberId, String memberPw, String memberNames, String memberBirth, String memberPhone,
			String saveFileName, String originalFilename) {
		MemberVO joinMember = new MemberVO();
		joinMember.setMemberId(memberId);
		joinMember.setMemberPw(memberPw);
		joinMember.setMemberNames(memberNames);
		joinMember.setMemberBirth(memberBirth);
		joinMember.setMemberPhone(memberPhone);
		joinMember.setMemberPhotoOr(originalFilename);
		joinMember.setMemberPhotoSt(saveFileName);
		
		return dao.join(joinMember) > 0 ? true : false;
	}

	public MemberVO login(String memberId, String memberPw) {
		MemberVO  loginMember = new MemberVO();
		
		loginMember.setMemberId(memberId);
		loginMember.setMemberPw(memberPw);
		
		return dao.login(loginMember);
	}

	public int idCheck(String memberId) {
		return dao.idCheck(memberId);
	}

	public MemberVO selectId(String memberId) {
		return dao.selectId(memberId);
	}

	public boolean updateMember(String memberId, String memberPw, String memberNames, String memberBirth,
			String memberPhone, String saveFileName, String originalFilename) {
		MemberVO updateMember = new MemberVO();
		updateMember.setMemberId(memberId);
		updateMember.setMemberPw(memberPw);
		updateMember.setMemberNames(memberNames);
		updateMember.setMemberBirth(memberBirth);
		updateMember.setMemberPhone(memberPhone);
		updateMember.setMemberPhotoOr(originalFilename);
		updateMember.setMemberPhotoSt(saveFileName);
		
		return dao.updateMember(updateMember) > 0 ? true : false;
	}

	

}
