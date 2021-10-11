package com.pet.web.vo;

import lombok.Data;

@Data
public class MessageVO {
	private int messageNum;		// 메세지 번호
	private int messageRoom;	// 방 번호
	private int tradeBoardNum;	// 거래번호
	private String sender;		// 보낸사람
	private String receiver;	// 받은사람
	private String chatContent;	// 보낸내용
	private String sendTime;	// 보낸 시간
	private String readTime;	// 받은 시간
	private int readChk;		// 읽었는지 체크(안읽 1, 읽 0)
	
	// 현재 사용자의 메세지 상대 nick을 담는다.
	private String other_nick;		
	// 현재 사용자의 메세지 상대 profile을 담는다.
	private String profile;
	// 현재 사용자 nick
	private String nick;
	// 안읽은 메세지 갯수 
	private int unread;
}
