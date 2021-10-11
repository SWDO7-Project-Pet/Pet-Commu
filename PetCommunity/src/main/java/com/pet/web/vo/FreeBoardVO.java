package com.pet.web.vo;

import java.util.ArrayList;

import lombok.Data;

@Data
public class FreeBoardVO {
	private int freeBoardNum;        // 자유게시판 번호(PK)
	private String freeBoardTitle;   // 자유게시판 제목 
	private String freeBoardContent; // 자유게시판 내용
	private String freeBoardIndate;  // 자유게시판 작성일
	private int freeBoardHits;       // 자유게시판 조회수
	private int freeBoardLikes;      // 자유게시판 좋아요수
	private String freeBoardPhotoOr; // 자유게시판 사진이름(원본)
	private String freeBoardPhotoSt; // 자유게시판 사진이름(저장)
	private String memberId;		 // 작성자
	private int freeBoardReplyCount; // 댓글 개수
	
	
	
	private ArrayList<FreeHashtagVO> freeHash; // 해시태그에 맞는 게시판 정보를 담는 리스트
}
