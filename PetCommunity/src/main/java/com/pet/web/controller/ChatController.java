
package com.pet.web.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.WebSocketSession;

import com.pet.web.service.ChatService;
import com.pet.web.vo.ChatVO;
import com.pet.web.vo.MemberVO;
import com.pet.web.vo.TimeUtils;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {
	
	private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
	
//	@Autowired
//	private final SimpMessagingTemplate simpMessage;
	
	@Autowired
	private ChatService service;
	
	// 채팅으로 거래하기(productInfo 화면)
	@RequestMapping(value="/chatMessage", method=RequestMethod.GET)
    public String getWebSocketWithSockJs(Model model, HttpSession session, 
            @ModelAttribute("chatRoom") ChatVO chatRoom) throws IOException {
        
        //productInfo화면에서 Chat화면에 전달해줄 parameter
        String login = (String) session.getAttribute("memberId");
        // 수신자 : 로그인된 아이디
        chatRoom.setSender(login);
               
        //이미 chatRoom이 만들어져있는지 확인
        if (service.countByChatId(chatRoom.getTradeBoardNum(), chatRoom.getSender()) > 0) {
            //get ChatRoomInfo
            ChatVO chatRoomTemp = service.findByChatId(chatRoom.getTradeBoardNum(), chatRoom.getSender());
            //load existing chat history
            List<ChatVO> chatHistory = service.readChatHistory(chatRoomTemp);
            //transfer chatHistory Model to View
            model.addAttribute("chatHistory", chatHistory);     
        } else {
            //chatRoom 생성            
            service.addChatRoom(chatRoom);            
            //text file 생성
            service.createFile(chatRoom.getTradeBoardNum(),service.getId(chatRoom.getTradeBoardNum(), chatRoom.getSender()));                                
        }
 
            //chatRoom Add 시 생성될 chatId
            chatRoom.setChatNum(service.getId(chatRoom.getTradeBoardNum(), chatRoom.getSender()));
            
            //chatRoom 객체 Model에 저장해 view로 전달
            model.addAttribute("chatRoomInfo", chatRoom);
        
        return "chat";
    }
	
    @MessageMapping("/broadcast")
    public void send(ChatVO chatRoom) throws IOException {
 
        chatRoom.setChatSendIndate(TimeUtils.getCurrentTimeStamp());
        //append message to txtFile
        service.appendMessage(chatRoom);
        
        int id = chatRoom.getChatNum();
//        String url = "/user/" + id + "/queue/messages";
//        simpMessage.convertAndSend(url, new ChatVO(chatRoom.getChatContent(), chatRoom.getSender(), chatRoom.getChatSendIndate()));     
        }
}
