package com.pet.web.vo;

import lombok.Data;

@Data
public class TradeBoardSaveVO {
	private int tradeBoardNum;           // 거래게시판 번호
	private String tradeBoardPhotoOr;    // 거래게시판 사진(원본)
	private String tradeBoardPhotoSt;    // 거래게시판 사진(저장)
}
