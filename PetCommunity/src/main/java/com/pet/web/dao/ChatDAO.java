package com.pet.web.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChatDAO {
	
	@Autowired
	private SqlSession session;

	// 채팅방 생성
	public int createChatRoom() {
		int result = 0;
		ChatMapper mapper = null;
		
		try {
			mapper = session.getMapper(ChatMapper.class);
			result = mapper.createChatRoom();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
