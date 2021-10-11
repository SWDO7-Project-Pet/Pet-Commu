package com.pet.web.vo;

import lombok.Data;

@Data
public class PhotoBoardSaveVO {
	private int photoboardNum;           // 사진게시판 번호
	private String photoBoardPhotoOr;    // 사진게시판 사진(원본) 
	private String photoBoardPhotoSt;    // 사진게시판 사진(저장)
}
