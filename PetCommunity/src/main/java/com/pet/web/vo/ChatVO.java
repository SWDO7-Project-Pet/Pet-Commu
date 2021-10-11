package com.pet.web.vo;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ChatVO {
	private int chatNum;			// 메신저 번호
	private int tradeBoardNum;		// 거래 테이블 번호
	private String sender;			// 보낸사람
	private String receiver;		// 받는사람
	private String fileName;
	private String chatContent;		// 보낸내용
	private String chatSendIndate;		// 보낸시간
	
	private String tradeBoardTitle;	// 거래 테이블 번호
    private Timestamp createdDate;  // 채팅창 만들어지는 시간	

    public ChatVO() {
    	
    }
    
	public ChatVO(String chatContent, String sender, String chatSendIndate) {
		this.chatContent = chatContent;
        this.sender = sender;
        this.chatSendIndate = chatSendIndate;
	}
}
