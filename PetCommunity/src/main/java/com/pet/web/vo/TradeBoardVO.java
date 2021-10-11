package com.pet.web.vo;

import lombok.Data;

@Data
public class TradeBoardVO {
	private int tradeBoardNum;        	  // 거래게시판 번호(PK)
	private String tradeBoardTitle;       // 거래게시판 제목 
	private String tradeBoardContent;     // 거래게시판 내용
	private String tradeBoardIndate;      // 거래게시판 작성일
	private int tradeBoardHits;           // 거래게시판 조회수
	private String tradeBoardThumbnailOr; // 거래게시판 썸네일이름(원본)
	private String tradeBoardThumbnailSt; // 거래게시판 썸네일이름(저장)
	private String memberId;		      // 작성자
	
	// 시작 게시물 번호
	private int startRowNum;
	// 끝 게시물 번호
	private int endRowNum;
	// 가져갈 게시물 갯수
	private int rowCount;
	
}
