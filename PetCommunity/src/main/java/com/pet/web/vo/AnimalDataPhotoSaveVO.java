package com.pet.web.vo;

import lombok.Data;

@Data
public class AnimalDataPhotoSaveVO {
	private int animalNum;           // 동물정보 번호
	private String adBoardPhotoOr;   // 동물정보 사진(원본) 
	private String adBoardPhotoSt;   // 동물정보 사진(저장)
}
