<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>사진 게시판</title>
		
		<!-- Custom fonts for this template-->
	    <link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
	    <link
	        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	        rel="stylesheet">
	
	    <!-- Custom styles for this template-->
	    <!-- <link href="resources/css/sb-admin-2.min.css" rel="stylesheet"> -->
	    <link href="/resources/css/sb-admin-2.css" rel="stylesheet">
	    <link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">
	    <link type="text/css" rel="stylesheet" media="all" href="/resources/mikes/lib/mikes-modal.css">
	    
	    <style type="text/css">
			/* Medium devices (tablets, 768px and up) The navbar toggle appears at this breakpoint */
			@media (min-width: 768px) {  
			  .card-columns {column-count: 4;}
			}
			
			/* Large devices (desktops, 992px and up) */
			@media (min-width: 992px) { 
			 .card-columns {column-count: 4;}
			}
			 
			/* Extra large devices (large desktops, 1200px and up) */
			@media (min-width: 1200px) {  
			   .card-columns {column-count: 5;} 
			}
	    </style>
	    
	   <!-- 해시태그s 인풋 -->
       <link href="/resources/tagsinput/tagsinput.css" rel="stylesheet" type="text/css">
	
		<!-- modal -->
        <link href="/resources/mikes/lib/mikes-modal.css" rel="stylesheet" type="text/css" media="all">
	
	    
	    	    	
	</head>
	
		<body id="page-top">
		<%@include file="/WEB-INF/views/menubar.jsp"%>
		    <!-- Page Wrapper -->
		    <div id="wrapper">
		    
		 		<!-- modal뛰우면 뜨는 내용 -->
					<!-- 모달창 사진창 부분 -->
					<c:forEach items="${photoBoardList }" var="pBoard">
					<div class="mikes-modal" id="myModal${pBoard.photoBoardNum }">
		
					<div id="carouselExampleIndicators" class="carousel" data-ride="carousel" style="float:left;">
					  <!-- 땡떙이 -->
					  <ol class="carousel-indicators">
					    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
					    <c:forEach items="${photoBoardSaveList }" var="photoBoardSaveList" varStatus="status">
						    <c:if test="${pBoard.photoBoardNum == photoBoardSaveList.photoboardNum}">
						    	<li data-target="#carouselExampleIndicators" data-slide-to="${status.index }"></li>
						  	</c:if>
					  	</c:forEach>
					  </ol>
						<!-- 썸넬먼저보여주고 나머지 사진 반복문돌림 -->
					  	<div class="carousel-inner">
						    <div class="carousel-item active">
						      <img src="/petcommunity/${pBoard.photoBoardThumbnailSt }" style="height: 800px;width: 500px;">
						    </div>
						
							<c:forEach items="${photoBoardSaveList }" var="photoBoardSaveList">
								<c:if test="${pBoard.photoBoardNum == photoBoardSaveList.photoboardNum}">
								   <div class="carousel-item">
							    	   <img src="/petcommunity/${photoBoardSaveList.photoBoardPhotoSt }" style="height: 800px;width: 500px">
							   	   </div>
								</c:if>
							</c:forEach>
							
						</div>
						
					  	<a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
					    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
					    <span class="sr-only">Previous</span>
					  	</a>
						<a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
						    <span class="carousel-control-next-icon" aria-hidden="true"></span>
						    <span class="sr-only">Next</span>
						</a>
					</div>	
					
			
					<div class="description">
					<div class="title-area">
		
						<!-- 모달창 내용부분 -->					
						<h1>${pBoard.memberId }</h1>
						<p>${pBoard.photoBoardContent }</p>
						
						<!-- ajax로 댓글리스트랑 작성하는 부분 보여줄려고햇던 부분 
						<form action="" method="post" id="replyTable" class = "table table-striped">
						</form>						
						<form action="" method="post" id="writeReply">
						</form> -->
						
						<!-- 모달창내용에서 댓글창보여주는부분 -->
						<table id="replyTable">
						  <c:forEach items="${photoReplyList }" var="photoReplyList">
						  <c:if test="${pBoard.photoBoardNum == photoReplyList.photoBoardNum}">
							<tr id="oneRowReply${photoReplyList.photoReplyNum }">
								<th>${photoReplyList.memberId }</th>
								<td id="pReply${photoReplyList.photoReplyNum }">
									<span id= "photoReplyContent${photoReplyList.photoReplyNum }">${photoReplyList.photoReplyContent }</span>
								</td>
								<td>${photoReplyList.photoReplyIndate }
									<c:if test="${photoReplyList.memberId == sessionScope.memberId }">
										<input type="button" value="수정" 
											onclick="upupReply(${photoReplyList.photoReplyNum },${photoReplyList.photoBoardNum });" class="btn btn-secondary btn-sm">
										<input type="button" value="삭제" onclick="deleteReply(${photoReplyList.photoReplyNum },${photoReplyList.photoBoardNum });" class="btn btn-secondary btn-sm">
									</c:if>
								</td>
						  	 </tr>
						  	 </c:if>
						 </c:forEach>
					   </table>
					   
					   <form action="/photoBoard/photoBoardReplyWrite" method="post">
							<textarea name="photoReplyContent" style="width:150px; resize:none;" placeholder="댓글을 입력하세요" rows="1"></textarea>
							
							<input type="submit" id="writeReply" value="등록" onclick="writeReply(${pBoard.photoBoardNum });">
							<input type="hidden" name="photoBoardNum" value="${pBoard.photoBoardNum }">
					   </form>
						
					</div>
					</div>
			
					</div>
				    </c:forEach>
				    
            <!-- 사진 게시판 화면 --> 
            <div class="container">
												   <!-- 글쓰기 버튼 -->
			  <h1>사진 게시판</h1><div align="right"><a href="/photoBoard/photoBoardWrite" class="btn btn-primary">글쓰기</a></div>
			  <div class="card-columns">			
		   
				    <!-- 게시물 전체 list for문 -->
				    <c:forEach items="${photoBoardList }" var="pBoard">
					    <div class="card">
					      <div class="card-header">${pBoard.memberId }
					      </div>	
					      
					        <!-- 썸넬이미지 보여주기부분 -->
					      	<img id="open-mikes-modal${pBoard.photoBoardNum }" class="card-img-top img-fluid" src="/petcommunity/${pBoard.photoBoardThumbnailSt }" onclick="photoModal(${pBoard.photoBoardNum });" alt="Card image cap" style="cursor:pointer;">
					     
					      
					      
					      <div class="card-body">
					 
					        <h6 class="card-title">${pBoard.photoBoardTitle }</h6>
					        
							<!-- 게시물에해당하는 해시태그보여주기부분 -->
					        <c:forEach items="${photoHashtagList }" var="photoHashtagList">
					        <c:if test="${pBoard.photoBoardNum == photoHashtagList.photoBoardNum}">
					        <input type="button" class="btn btn-primary" value="${photoHashtagList.hashtag }">					        
					        </c:if>
					        </c:forEach>
					        
					        <br>
					        <!--  좋아요보여주기부분 -->
					        <c:forEach items="${photoLikesList }" var="photoLikes">					        
					  		<c:if test="${photoLikes.PHOTO_BOARD_NUM == pBoard.photoBoardNum }">
					  			<!-- 임의변수 선정해서 해당글에 좋아요 한 상태면 숫자 1 -->
					  			<c:set var="flag" value="1"></c:set>
					  		</c:if>
					  		</c:forEach>
							<!-- 게시물에대해 좋아요 햇으면 하트표시되잇음 -->
					  		<c:if test="${flag == 1}">
					  		<img src="/resources/img/heart.png" id="deleteBtn${pBoard.photoBoardNum }" onclick="deleteFavoite(${pBoard.photoBoardNum }, ${pBoard.photoBoardLikes });" height ="25" width ="25" style="cursor:pointer;">
					  		<span id="favoriteTd${pBoard.photoBoardNum }">${pBoard.photoBoardLikes }</span>
					  		</c:if>
					  		
					  		<!-- 좋아요 안누른글은 빈하트표시있음  -->
					  		<c:if test="${flag != 1}">
					  		<img src="/resources/img/emptyheart.png" id="favoriteBtn${pBoard.photoBoardNum }" onclick="favorite(${pBoard.photoBoardNum }, ${pBoard.photoBoardLikes });" height ="25" width ="25" style="cursor:pointer;">
					  		<span id="favoriteTd${pBoard.photoBoardNum }">${pBoard.photoBoardLikes }</span>
					  		</c:if>
					  		<!-- flag 초기화해줘야함-->
					  		<c:set var="flag" value="0"></c:set>
					  	 
					  	 	<!-- 자신이 쓴글이면 수정 삭제 표시 -->
					  	    <c:if test="${sessionScope.memberId == pBoard.memberId }">
						  	  <br>
						      <div class="btn float-right"><a href="/photoBoard/photoBoardUpdate?photoBoardNum=${pBoard.photoBoardNum }" class="btn btn-primary btn-sm">수정</a>
						      <a href="/photoBoard/photoBoardDelete?photoBoardNum=${pBoard.photoBoardNum }" class="btn btn-secondary btn-sm">삭제</a></div>
					        </c:if>
					  		<input type="hidden" id="photoBoardNum" value="${pBoard.photoBoardNum }">
					      </div>
					    </div>
				   
				    </c:forEach>
				 </div>
				
 		     </div> 
				    		     	  	
			 </div>
			<!-- /.card-columns -->
		</div>
		<!-- /.container -->
				            
			
		<!-- Scroll to Top Button-->
		 <a class="scroll-to-top rounded" href="#page-top">
		     <i class="fas fa-angle-up"></i>
		 </a>
				    
		<!-- Bootstrap core JavaScript-->
	    <script src="/resources/vendor/jquery/jquery.min.js"></script>
	    <script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	
	    <!-- Core plugin JavaScript-->
	    <script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
	
	    <!-- Custom scripts for all pages-->
	    <script src="/resources/js/sb-admin-2.min.js"></script>
		
		<script type="text/javascript" src="../resources/masonry-docs/masonry.pkgd.min.js"></script>
		<!-- <script type="text/javascript">
		    jQuery(document).ready(function () {
		        jQuery("#masonry_container").masonry({
		              itemSelector : ".item"
		            , columnWidth : 110
		        });
		    });
		</script> -->
		
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<script src="/resources/mikes/lib/mikes-modal.min.js" type="text/javascript"></script>

    	<script>	
		     jQuery(function() {
		        $("#open-mikes-modal").click(function(e) {
		          e.preventDefault();
		          $("#myModal").mikesModal();
		        });
		      });
		     
		     function ex(){
		    	 $("#myModal").mikesModal();
		     }
    	</script>
    	
    	<script type="text/javascript">
	
			/* 좋아요하는 펑션 */
			function favorite(photoBoardNum, photoBoardLikes){
				
				//confirm(photoBoardNum);
				 $.ajax({
					url:"/photoBoard/photoBoardInsertFavorite"
					,type: "post"
					,data: {
						photoBoardNum:photoBoardNum
					}
				 	,success:function(){
				 		//alert("좋아요반영");
				 		photoBoardLikes +=1;
						$("#favoriteTd" + photoBoardNum).text(photoBoardLikes);
						$("#favoriteBtn"+photoBoardNum).attr("src", "/resources/img/heart.png");
						$("#favoriteBtn"+ photoBoardNum).attr("onclick", "deleteFavoite(photoBoardNum,photoBoardLikes);");
						/* 그냥페이지 새로고침 기능바로 전환 못함ajax로해야하는듯 */
						location.reload();
					//	$("#favoriteBtn" + photoBoardNum).load("/photoBoard/photoBoardDeleteFavorite #deleteFavoite"+photoBoardNum);
				 	}
					,error:function(e){
						console.log(e);
					}
				}); 
			}
			/* 좋아요 취소하는 펑션 */
			function deleteFavoite(photoBoardNum, photoBoardLikes){
				
				//confirm(photoBoardNum);
				 $.ajax({
					url:"/photoBoard/photoBoardDeleteFavorite"
					,type: "post"
					,data: {
						photoBoardNum:photoBoardNum
					}
				 	,success:function(){
				 		//alert("좋아요 취소 반영");
				 		photoBoardLikes -=1;
				 		
					//	$("#favoriteTd" + photoBoardNum).html("favoriteBtn"+photoBoardNum);
						$("#favoriteTd" + photoBoardNum).text(photoBoardLikes);
						$("#deleteBtn"+ photoBoardNum).attr("src", "/resources/img/emptyheart.png");
						$("#deleteBtn"+ photoBoardNum).attr("onclick", "favorite(photoBoardNum,photoBoardLikes);");
						/* 그냥페이지 새로고침 기능바로 전환 못함ajax로해야하는듯 */
						location.reload();
							
				 	}
					,error:function(e){
						console.log(e);
					}
				}); 
			}
			/* 모달펑션 실행하고 댓글리스트 보여주는 getlist실행 하지만 안됨 */
			function photoModal(photoBoardNum) {
			  $("#open-mikes-modal"+photoBoardNum).click(function(e) {
				  alert("모달 펑션실행"+photoBoardNum);
			    e.preventDefault();
			    $("#myModal"+photoBoardNum).mikesModal();
			    
			   
			    getReplyList(photoBoardNum);
			    
			  });
			};
			
			/* 댓글 삭제 펑션 기능실행되면 hide로 숨기기*/
			function deleteReply(photoReplyNum,photoBoardNum){
				var result = confirm("정말로 댓글을 삭제하시겠습니까?");
				if(result){
					$.ajax({
						url: "/photoBoard/photoBoardReplyDelete",
						type : "get",
						data :{
							photoReplyNum:photoReplyNum
							,photoBoardNum:photoBoardNum
							
						}
						,success :function(){
							//바로 밑에꺼 실행안되는거같은디
							$("#pReply" + photoReplyNum).load("/photoBoard/replyList #pReply"+photoReplyNum);
							$("#oneRowReply"+ photoReplyNum).hide(); 
						}
					
					})
					
				
				}											
			}
			
			
			/* 업데이트 하는 펑션 수정은되는데 페이지 이상한데로 샘*/
			function upupReply(photoReplyNum,photoBoardNum){
				var result = confirm("정말로 댓글을 수정하시겠습니까?");
				if(result){
					$.ajax({
						url : "/photoBoard/photoBoardReplyUpdate",
						type : "post",
						data :{
							photoReplyNum:photoReplyNum
							,photoBoardNum:photoBoardNum
						}
						,success :function(data){
							alert("수정 function실행");
							var td = document.getElementById("pReply"+photoReplyNum);
							var photoReplyContent = document.getElementById("photoReplyContent"+photoReplyNum).innerHTML;
							var str = "";
							
							str += "<form action ='/photoBoard/photoBoardReplyUpdate' method='post'>";
							str += "		<textarea name='photoReplyContent'>";
							str += 			photoReplyContent;
							str += "		</textarea>";
							str += "		<input type ='submit' value='수정'>";
							str += " 		<input type ='hidden' name='photoReplyNum' value='"+photoReplyNum+"'>"; //유동적으로 변하는값을 표현하기 위해 끊은것임
							str += "		<input type ='hidden' name='photoBoardNum' value='"+photoBoardNum+"'>";  
							str += "</form>";
							
							td.innerHTML = str;
			
						}
					
					})
					
				}											
			}
			
	</script>
	

	
	<!-- <script type="text/javascript">
		/* 댓글쓰는 펑션 */
		function writeReply(photoBoardNum){
			$.ajax({
				url: "/photoBoard/photoBoardReplyWrite",
				type: 'POST',
				success: function(data){
					alert("writeReply성공 " + photoBoardNum);
					getReplyList(photoBoardNum);
					/* $(document).ready(function(){
						photoModal(photoBoardNum);
					}); */
				}
			});
		}
		
		
		/* 댓글리스트 불러오기위한 펑션 */
		function getReplyList(photoBoardNum){
			alert("되니 안되니");
			$.ajax({
				url: "/photoBoard/replyList",
				type: "POST",
				dataType: "json",
				success: function(){
					alert("getReplyListAjax성공");
				//	getReplyListOK(); //photoBoardNum 보내줘야하는뎅..그냥적음되낭
				}
			});
		}
		
		/* 댓글리스트 불러오는 HTML 펑션 *//*json.photoBoardNum 하면오나? 아니면 위에서 보내줘야하나?  */
		function getReplyListOK(json){
			var td = document.getElementById("replyTable");
			var str = "";
	
			var str = "<table style='text-decoration: none;text-align: center;'>";
			
			$.each(json.list, function(key, item){
				if(photoBoardNum ==item.photoBoardNum){
					str += "<tr id='oneRowReply'"+item.photoReplyNum+"' name='oneRowReply'"+item.photoReplyNum+"'>";
					str += "<td><input type='text' name='memberName' value='"+item.memberId+"' size='10' style='border-style: none;' readonly='readonly'></td>';
					str += "<td id='pReply'"+item.photoReplyNum+"'>';
					str += "<span id='photoReplyContent'"+item.photoReplyNum+"'>"item.photoReplyContent"</span>';
					str += "</td>";
					str += "<td>"+item.photoReplyIndate+"<br>';
					if(list.memberId == session.scopreId){
					str += "<input type='button' value='수정' onclick='upupReply("+item.photoReplyNum+","+item.photoBoardNum+");' class='btn btn-secondary btn-sm'>";
					str += "<input type='button' value='삭제' onclick='deleteReply("+item.photoReplyNum+","+item.photoBoardNum+");' class='btn btn-secondary btn-sm'>";
					}
					str += "</td>";
					str += "</tr>";
				}
			}
					str+="</table>";
		
			td.html(str);
		}
		
		
		/* 업데이트 하는 펑션  이거랑 밑에 합쳐서 위로 올려놈*/
		function upupReply(photoReplyNum,photoBoardNum){
			var result = confirm("정말로 댓글을 수정하시겠습니까?");
			if(result){
				$.ajax({
					data :{
						photoReplyNum:photoReplyNum
						,photoBoardNum:photoBoardNum
					}
					,success :function(data){
						alert("수정 function실행");
						updateReply(data);
					}
				
				})
				
			}											
		}
		/* 업데이트하면 보영지는거..? */
		function updateReply(photoReplyNum,photoBoardNum){
		var td = document.getElementById("pReply"+photoReplyNum);
		var photoReplyContent = document.getElementById("photoReplyContent"+photoReplyNum).innerHTML;
		var str = "";
		
		str += "<form action ='/photoBoard/photoBoardReplyUpdate' method='post'>";
		str += "		<textarea name='photoReplyContent'>";
		str += 			photoReplyContent;
		str += "		</textarea>";
		str += "		<input type ='submit' value='수정'>";
		str += " 		<input type ='hidden' name='photoReplyNum' value='"+photoReplyNum+"'>"; //유동적으로 변하는값을 표현하기 위해 끊은것임
		str += "		<input type ='hidden' name='photoBoardNum' value='"+photoBoardNum+"'>";  
		str += "</form>";
		
		td.innerHTML = str;
		}
		
		
		
	</script> -->

	</body>
</html>

