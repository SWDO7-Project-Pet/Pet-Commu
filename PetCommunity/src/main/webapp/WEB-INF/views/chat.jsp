<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">			
		<title>웹소켓 채팅</title>	
		
		<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    	<link rel="stylesheet" href="/resources/css/chatBroadcastProduct.css">
	</head>
	<body>
		 <div class="container">
	        <div class="title_text">
	            <h2>제목</h2>
	        </div>
	        <div class="row">    
	                <%--chatHistory와 member가 실시간 입력하는 메시지 출력 --%>
	                <div id="content">
	                    <c:forEach var="chatRoom" items="${chatHistory}">
	                        <p>
	                            <span id="chatRoomSenderId">${chatRoom.sender}</span><br>
	                            <span id="chatRoomContent">${chatRoom.chatContent}</span><br>
	                            <span id="chatRoomSendTime">${chatRoom.chatSendIndate}</span><br>
	                        </p>    
	                    </c:forEach>
	                </div>
	                <%--메시지 입력창과 보내기 버튼 --%>
	                <div class="row_3">
	                    <div class="input_group" id="sendMessage">
	                        <input type="text" placeholder="Message" id="message" class="form_control"/>
	                        <div class="input_group_append">
	                            <button id="send" class="btn btn-primary" onclick="send()">보내기</button>
	                            <input type="hidden" value="${sessionScope.memberId}" id="buyerName"/>
	                            <input type="hidden" value="${chatRoomInfo.tradeBoardNum}" id="pr_id"/>
	                            <input type="hidden" value="${chatRoomInfo.recevier}" id="sellerId"/>
	                            <input type="hidden" value="${chatRoomInfo.chatNum}" id="id"/>                        
	                        </div>                    
	                    </div>                
	                </div>
	            </div>
    		</div>
    
		    <%-- STOMP와 sockjs webjars import --%>
		    <script src="/webjars/stomp-websocket/2.3.3-1/stomp.js" type="text/javascript"></script>
		    <script src="/webjars/sockjs-client/1.1.2/sockjs.js" type="text/javascript"></script>
		    
		    <script type="text/javascript">
		    
		    </script>
		
	</body>
</html>