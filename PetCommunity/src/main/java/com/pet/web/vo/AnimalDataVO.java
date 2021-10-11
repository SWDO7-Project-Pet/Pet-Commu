package com.pet.web.vo;

import lombok.Data;

@Data
public class AnimalDataVO {
	private int animalNum;				// 동물정보 번호	
	private String animalKind;			// 동물 종류
	private String animalThumbnailOr;	// 동물정보 썸내일(원본)
	private String animalThumbnailSt;	// 동물정보 썸내일(저장)
	private String animalVariety;		// 동물 품종
	private String animalOutline;		// 동물 썸네일설명
	private String animalOrigin;       // 동물 원산지
	private String animalSize;          // 동물 크기
	private String animalHeight;      // 동물 체고
	private String animalWeight;     // 동물 체중
	private String animalAppearance; //동물 외모
	private String animalColor;        // 동물 색상
	private String animalPersonality; //동물 성격
	private String animalPurpose;     //동물 용도
	private String animalDisease;     //동물 유의할질병
	private String animalRecommend;     //동물 추천성향
	private String memberId;			// 작성자(관리자)
}