package com.pet.web.vo;

import lombok.Data;

@Data
public class PhotoBoardVO {
	private int photoBoardNum;        	  // 사진게시판 번호(PK)
	private String photoBoardTitle;       // 사진게시판 제목 
	private String photoBoardContent;     // 사진게시판 내용
	private String photoBoardIndate;      // 사진게시판 작성일
	private int photoBoardHits;           // 사진게시판 조회수
	private int photoBoardLikes;          // 사진게시판 좋아요수
	private String photoBoardThumbnailOr; // 사진게시판 썸네일이름(원본)
	private String photoBoardThumbnailSt; // 사진게시판 썸네일이름(저장)
	private String memberId;		      // 작성자
}
