package com.pet.web.vo;

import lombok.Data;

@Data
public class PhotoReplyVO {
	private int photoReplyNum;			// 사진게시판 댓글번호
	private String photoReplyContent;	// 사진게시판 댓글내용
	private String photoReplyIndate;	// 사진게시판 댓글작성일
	private int photoBoardNum;			// 사진게시판 글번호
	private String memberId;			// 사진게시판 댓글작성자
}
