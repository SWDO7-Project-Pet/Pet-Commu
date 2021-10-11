package com.pet.web.vo;

import lombok.Data;

@Data
public class MemberVO {
	private String memberId;    	// ID
	private String memberPw;    	// PW
	private String memberNames;  	// 이름
	private String memberBirth; 	// 생년월일
	private String memberPhone; 	// 폰번호
	private String memberPhotoOr; 	// 프로필 사진(원본)
	private String memberPhotoSt; 	// 프로필 사진(저장)
	private int memberCode;     	// 회원(0), 관리자(9) 나누는 코드
	
}
