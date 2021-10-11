package com.pet.web.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pet.web.dao.ChatMapper;
import com.pet.web.vo.ChatVO;

@Service
@Transactional
public class ChatService implements ChatMapper{
	
	@Autowired
	ChatMapper mapper;
	
	@Value("${file.upload.path.txt}")
	String fileUploadPath;
	
	// 채팅방 생성
	@Override
	public void addChatRoom(ChatVO chatRoom) throws IOException {	        
		Timestamp createdDate = Timestamp.valueOf(LocalDateTime.now());	        
	    chatRoom.setCreatedDate(createdDate);
	    mapper.addChatRoom(chatRoom);	        
	}
	 
	//no connection with DB
    public List<ChatVO> readChatHistory(ChatVO chatRoom) throws IOException {
        
        String pathName = fileUploadPath + chatRoom.getFileName();
        
        //DB에 저장된 chat.txt 파일을 읽어옴 
        BufferedReader br = new BufferedReader(new FileReader(pathName));
        //View에 ChatRoom 객체로 전달
        ChatVO chatRoomLines = new ChatVO();
        List<ChatVO> chatHistory = new ArrayList<ChatVO>();
 
        String chatLine;
        int idx = 1;
        
        while ((chatLine = br.readLine()) != null) {
            
            //1개 메시지는 3줄(보낸사람,메시지내용,보낸시간)로 구성돼있음
            int answer = idx % 3;
            if (answer == 1) {
                //보낸사람
                chatRoomLines.setSender(chatLine);
                idx++;
            } else if (answer == 2) {
                //메시지내용
                chatRoomLines.setChatContent(chatLine);
                idx++;
            } else {
                //보낸시간
                chatRoomLines.setChatSendIndate(chatLine);
                //메시지 담긴 ChatRoom 객체 List에 저장
                chatHistory.add(chatRoomLines);
                //객체 초기화, 줄(row)인덱스 초기화
                chatRoomLines = new ChatVO();
                idx = 1;
            }            
        }
        
        return chatHistory;
    }
    
    @Override
    public void updateFileName(int id, String fileName) { 
        mapper.updateFileName(id, fileName);
    }
    
    public void createFile(int tradeBoardNum, int id) throws IOException {
        
        String fileName = tradeBoardNum + "_" + id + ".txt";
        String pathName = fileUploadPath + fileName;
        //File 클래스에 pathName 할당
        File txtFile = new File(pathName);
        //로컬경로에 파일 생성
        txtFile.createNewFile();
        
        mapper.updateFileName(id, fileName);
    }
    
    @Override
	public int countByChatId(int pr_id, String buyerId) {		
		return mapper.countByChatId(pr_id, buyerId);
	}
    
    @Override
	public int getId(int pr_id, String buyerId) {		
		return mapper.getId(pr_id, buyerId);
	}
    
    //no connection with DB
  	public void appendMessage(ChatVO chatRoom) throws IOException {
  		
  		int pr_id = chatRoom.getTradeBoardNum();
  		String buyerId = chatRoom.getSender();
  		
  		ChatVO chatRoomAppend = mapper.findByChatId(pr_id, buyerId);
  				
  		String pathName = fileUploadPath + chatRoomAppend.getFileName();
  		
  		FileOutputStream fos = new FileOutputStream(pathName, true);
  		String content = chatRoom.getChatContent();
  		String senderId = chatRoom.getSender();
  		String sendTime = chatRoom.getChatSendIndate();
  		System.out.println("print:" + content);
  		
  		String writeContent = senderId + "\n" + content + "\n" + "[" +  sendTime + "]" + "\n";
  		
  		byte[] b = writeContent.getBytes();
  		
  		fos.write(b);
  		fos.close();
  		
  		System.out.println("senderId: "+ senderId);
  		System.out.println("sellerId: "+ chatRoom.getReceiver()); // 판매자
  		System.out.println(senderId.equals(chatRoom.getReceiver()));
  		if (senderId.equals(chatRoom.getReceiver())) {
  			updateChatReadBuy(chatRoom.getChatNum(), 0);
  		} else {
  			updateChatReadSell(chatRoom.getChatNum(), 0);
  		}
  		
  	}

	public void updateChatReadSell(int id, int chatReadSell) {
	// TODO Auto-generated method stub
		mapper.updateChatReadSell(id, chatReadSell);
	
}

	public void updateChatReadBuy(int id, int chatReadBuy) {
		// TODO Auto-generated method stub
		mapper.updateChatReadBuy(id, chatReadBuy);
	}

	@Override
	public ChatVO findByChatId(int pr_id, String buyerId) {
		// TODO Auto-generated method stub
		return null;
	}
}
