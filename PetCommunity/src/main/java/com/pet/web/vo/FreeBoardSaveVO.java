package com.pet.web.vo;

import lombok.Data;

@Data
public class FreeBoardSaveVO {
	private int freeboardNum;           // 사진게시판 번호
	private String freeBoardPhotoOr;    // 사진게시판 사진(원본) 
	private String freeBoardPhotoSt;    // 사진게시판 사진(저장)
}
