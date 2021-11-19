package com.pet.web.dao;

import org.apache.ibatis.annotations.Param;

public interface ChatMapper {

	// 채팅방 생성
	int createChatRoom();

}
