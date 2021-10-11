package com.pet.web.vo;

import lombok.Data;

@Data
public class TipVO {
	private int tipNum;			// 팁 번호
	private String tipTitle;	// 팁 제목
	private String tipContent;	// 팁 내용
	private String tipIndate;	// 팁 작성일
	private String memberId;	// 팁 작성자(관리자)
}
