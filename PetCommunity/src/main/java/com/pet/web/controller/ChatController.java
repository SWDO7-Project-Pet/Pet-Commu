package com.pet.web.controller;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pet.web.service.ChatService;
import com.pet.web.service.TradeBoardService;
import com.pet.web.vo.TradeBoardVO;

@Controller
@RequestMapping(value = "/chat")
public class ChatController {
	
	private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
	
	@Autowired
	ChatService chatService;
	
	@Autowired
	TradeBoardService tradeService;
	
	// 채팅방으로
	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public String createChatRoom() {
		return "chat/chat";
	}
	
	// 채팅방 생성
	@RequestMapping(value = "/createChatRoom", method = RequestMethod.POST)
	public String createChatRoom(Model model, HttpSession session) {
		logger.info("거래 신청을 누르면");
		
		// 채팅방 생성
		boolean result = chatService.createChatRoom();
		
		String returnUrl = null;
		
		if(result) {
			logger.info("채팅방 생성");
			returnUrl = "/chat/chat";
		} else {
			logger.info("채팅방 생성 실패");
			returnUrl = "home";
		}
		
		return returnUrl;
	}
	
	// 채팅방으로
	@RequestMapping(value = "/chat2", method = RequestMethod.GET)
	public String chat2() {
		return "chat/chat2";
	}
}
