package com.pet.web.vo;

import lombok.Data;

@Data
public class MessageVO {
	private int chatNum;   			// 채팅 번호
	private int chatRoomNum;  		// 채팅방 번호
	private String sender;	 		// 보낸 사람
	private String receiver; 		// 받는 사람
	private String chatContent; 	// 채팅 내용
	private String sendTime;		// 보낸 시간
}
