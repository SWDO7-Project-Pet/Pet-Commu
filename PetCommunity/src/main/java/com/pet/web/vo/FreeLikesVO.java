package com.pet.web.vo;

import lombok.Data;

@Data
public class FreeLikesVO {
	private String memberId;  // 좋아요 누른 ID
	private int freeBoardNum; // 좋아요 눌린 자유게시판
}
