package com.pet.web.vo;

import lombok.Data;

@Data
public class FreeReplyVO {
	private int freeReplyNum;			// 자유게시판 댓글번호
	private String freeReplyContent;	// 자유게시판 댓글내용
	private String freeReplyIndate;		// 자유게시판 댓글작성일
	private int freeBoardNum;			// 자유게시판 글번호
	private String memberId;			// 자유게시판 댓글작성자
}
