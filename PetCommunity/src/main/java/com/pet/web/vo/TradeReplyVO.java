package com.pet.web.vo;

import lombok.Data;

@Data
public class TradeReplyVO {
	private int tradeReplyNum;			// 거래게시판 댓글번호
	private String tradeReplyContent;	// 거래게시판 댓글내용
	private String tradeReplyIndate;	// 거래게시판 댓글작성일
	private int tradeBoardNum;			// 거래게시판 글번호
	private String memberId;			// 거래게시판 댓글작성자
	private String csOpen;				// 거래게시판 댓글 공개 여부
}
