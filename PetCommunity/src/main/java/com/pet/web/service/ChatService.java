package com.pet.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.web.dao.ChatDAO;

@Service
public class ChatService {

	@Autowired
	private ChatDAO dao;
	
	// 채팅방 생성
	public boolean createChatRoom() {
		
		return dao.createChatRoom() > 0 ? true:false;
	}

}
