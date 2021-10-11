<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	 						<c:forEach items="${list }" var="tradeBoard">		                    
			                    <div class="col mb-5 post">
			                         <div class="card h-100">
			                            <!-- 썸내일 이미지-->
			                            <img class="image" class="card-img-top" src="/petcommunity/${tradeBoard.tradeBoardThumbnailSt }" style="cursor:pointer;"
			                             onclick="location.href='tradeBoardRead?tradeBoardNum=' + ${tradeBoard.tradeBoardNum}" />
			                            <!-- Product details-->
			                            <div class="card-body p-4">
			                                <div class="text-center">
			                                    <!-- Product name-->
			                                    <h5 class="fw-bolder">${tradeBoard.tradeBoardTitle }</h5>
			                                    <!-- Product price-->	                                    
			                                </div>
			                            </div>
			                            <!-- Product actions-->
			                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
			                            	<div align="right">${tradeBoard.memberId }</div>
											<div align="right">조회 <span style="font-weight: bold;">${tradeBoard.tradeBoardHits }</span> | ${tradeBoard.tradeBoardIndate }</div>
			                        	</div>
			                    	</div>             
			                	</div>
			                </c:forEach> 
			                
	</body>
	</html>