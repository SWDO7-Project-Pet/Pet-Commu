package com.pet.web.dao;

import java.io.IOException;

import com.pet.web.vo.ChatVO;

public interface ChatMapper {

	void addChatRoom(ChatVO chatRoom) throws IOException;

	void updateFileName(int id, String fileName);

	int countByChatId(int pr_id, String buyerId);

	int getId(int pr_id, String buyerId);

	ChatVO findByChatId(int pr_id, String buyerId);

	void updateChatReadBuy(int id, int chatReadBuy);

	void updateChatReadSell(int id, int chatReadSell);

}
