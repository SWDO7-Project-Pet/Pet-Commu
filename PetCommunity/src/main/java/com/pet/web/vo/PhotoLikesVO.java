package com.pet.web.vo;

import lombok.Data;

@Data
public class PhotoLikesVO {
	private String memberId;   // 좋아요 누른 ID
	private int photoBoardNum; // 좋아요 눌린 사진게시판
}
