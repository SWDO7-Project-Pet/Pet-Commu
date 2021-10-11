<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <meta name="description" content="">
	    <meta name="author" content="">
	
	    <title>${freeBoard.freeBoardTitle }</title>
	
	    <!-- Custom fonts for this template -->
	    <link href="../resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
	    <link
	        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	        rel="stylesheet">
	
	    <!-- Custom styles for this template -->
	    <link href="../resources/css/sb-admin-2.min.css" rel="stylesheet">
	    <link href="../resources/css/sb-admin-2.css" rel="stylesheet">
	
	    <!-- Custom styles for this page -->
	    <link href="../resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
	    
	    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

	</head>

	<body id="page-top">
		<%@include file="/WEB-INF/views/menubar.jsp"%>
    <!-- Page Wrapper -->
    <div id="wrapper">
	
       

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    
                    <!-- 여기에 추가 -->
                  	<!-- 게시물 내용 출력 -->
					<div class="container my-1">
					
						<h1 class="h3 mb-2 text-gray-800">
							<span onclick="location.href='freeBoardMain'" style="cursor:pointer;">자유 게시판</span>							
						</h1>
						
							<span>
								<input type="button" value="목록으로" class="btn float-right" onclick="location.href='freeBoardMain'">
								<!-- 작성자와 로그인된 아이디가 같다면 -->
								<c:if test="${sessionScope.memberId == freeBoard.memberId || sessionScope.code != null}">
									<input type="button" value="수정" class="btn float-right" onclick="location.href='freeBoardUpdate/?freeBoardNum=${freeBoard.freeBoardNum }';">
									<input type="button" value="삭제" class="btn float-right" onclick="deleteCheck();">									
								</c:if>					
							</span>	
	
										
						<!-- 글 읽기 -->
						<div class="table">
							<table class="table">
								<!-- thead는 제목, 작성자 등 -->
								<thead class="thead-dark">
									<tr class="table-light">								
										<th scope="col">
											<div class="col">
												<div id="hashtag">
													<c:forEach items="${hashtags }" var="ht">
														#${ht.hashTag }
													</c:forEach>
												</div>
												<h2>${freeBoard.freeBoardTitle }</h2>												
												<span>${freeBoard.memberId }</span>
												<span>|</span>
												<span>${freeBoard.freeBoardIndate }</span>
											</div>
											
											<div class="col" align="right">
												<span>조회 ${freeBoard.freeBoardHits }</span>
												<span>|</span>
												<span id="likesSpan">
													추천 ${freeBoard.freeBoardLikes } <span id="rec_count"></span>
												</span>
												<span>|</span>
												<span>댓글 ${replyCount }</span>
											</div>
										</th>
									</tr>
								</thead>
								<!-- tbody는 내용과 추천 -->
								<tbody>
									<tr class="table-light">
										<td colspan="3">
											${freeBoard.freeBoardContent }										
										</td>
									</tr>
									<tr align="center" class="table-light">
										<td colspan="3">
											<span>
												<!-- 비로그인 시 -->
												<c:if test="${sessionScope.memberId == null}">
													<button type="button" class="btn btn-outline-danger" onclick="ale();" id="likesBtn">좋아요</button>
												</c:if>
												<!-- 로그인 시 -->
												<c:if test="${sessionScope.memberId != null}">
													<!-- 추천 버튼 -->
													<!-- <img src="/resources/img/icon/good.png" width="40px" height="40px" alt="좋아요" 
													style="cursor:pointer;" onclick="likes();" id="likesBtn"> -->
													<button type="button" class="btn btn-outline-danger" onclick="likes();" id="likesBtn">좋아요</button>
												</c:if>
													
											</span>
											
											<span>
												<button type="button" class="btn btn-outline-danger">신고</button>
											</span>
										</td>
									</tr>
								</tbody>								
							</table>
							
							
							<!-- 댓글 테이블 -->
							<table class="table table-sm">
								<c:forEach items="${freeReplyList }" var="freeReply">
									<tr>
										<th>${freeReply.memberId }</th>
										
										<td id="freeReply${freeReply.freeReplyNum }" style="width: 70%">
											<span id="freeReplyContent${freeReply.freeReplyNum }">${freeReply.freeReplyContent }</span>					
										</td>	
												
										<td align="right">
											<span>
												${freeReply.freeReplyIndate }
												<!-- 자신이 작성한 글 일 시 -->
												<c:if test="${freeReply.memberId == sessionScope.memberId || sessionScope.code != null}">
													<img src="/resources/img/icon/delete.png" width="20px" height="20px" alt="삭제" style="cursor:pointer;" onclick="deleteFreeReply(${freeReply.freeReplyNum});">
													<img src="/resources/img/icon/modifiy.png" width="20px" height="20px" alt="수정" style="cursor:pointer;" onclick="updateReply(${freeReply.freeReplyNum});">
												</c:if>
											</span>	
										</td>	
									</tr>
								</c:forEach>
								
									<tr>
										<td colspan="3">
											<form action="/freeBoard/freeReplyWrite" method="post">
												<div class="mb-3">
													<textarea class="form-control" name="freeReplyContent" id="exampleFormControlTextarea1" rows="3" style="resize: none;"></textarea>
												</div>
												<div align="right">
													<c:if test="${sessionScope.memberId == null}">
														<button type="button" class="btn btn-outline-primary" onclick="ale();">댓글 등록</button>
													</c:if>
													<c:if test="${sessionScope.memberId != null}">
														<button type="submit" class="btn btn-outline-primary">댓글 등록</button>
													</c:if>
												</div>
												<input type="hidden" name="freeBoardNum" value="${freeBoard.freeBoardNum }">
											</form>
										</td>
									</tr>
									
							</table>
						</div>
					</div>
                  	
                  	
                 
                 <!-- 글 삭제 폼 -->
                 <form id="deleteForm" action="/freeBoard/freeBoardDelete" method="post">
					<input type="hidden" name="freeBoardNum" value="${freeBoard.freeBoardNum }">
				 </form>
                 
		
                   
    
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>요기도 머쓸까</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->
    </div>
	<!-- End of Page Wrapper -->
	
	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>
	
	<input type="hidden" id="freeBoardNum" value="${freeBoard.freeBoardNum }">
	<input type="hidden" id="memberId" value="${freeBoard.memberId }">

    <!-- Bootstrap core JavaScript-->
    <script src="../resources/vendor/jquery/jquery.min.js"></script>
    <script src="../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="../resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="../resources/js/sb-admin-2.min.js"></script>
    <!-- <script src="/resources/js/jquery-3.6.0.min.js"></script> -->
    
    <script type="text/javascript">
    	// 글 삭제
	    function deleteCheck(){
			var result = confirm("정말 삭제하시겠습니까?");
			
			if(result){
				// 폼객체.submit() 폼객체를 제출한다.
				document.getElementById("deleteForm").submit();
			} else{
				
			}
		}
    	
		// 댓글 삭제
		function deleteFreeReply(freeReplyNum){
			var result = confirm("정말 삭제하시겠습니까?");
			
			if(result){
				location.href = "/freeBoard/freeReplyDelete?freeReplyNum=" + freeReplyNum + "&freeBoardNum=" + ${freeBoard.freeBoardNum };	
			}
		}
    	
		// 댓글 수정
		function updateReply(freeReplyNum){
			var tr = document.getElementById("freeReply" + freeReplyNum);
			var freeReplyContent = document.getElementById("freeReplyContent" + freeReplyNum).innerHTML;
			
			var str = "";
			str += "<form action='/freeBoard/freeReplyUpdate' method='post'>";
			str += "	<textarea class='form-control' name='freeReplyContent' id='exampleFormControlTextarea1' rows='3' style='resize: none;'>";
			str += 			freeReplyContent;
			str += 		"</textarea>";
			str += "	<div align='right'>" 
			str += "		<input type='submit' value='댓글 등록' class='btn btn-outline-primary'>";
			str += "	</div>"
			str += "	<input type='hidden' name='freeReplyNum' value='" + freeReplyNum + "'>";
			str += "	<input type='hidden' name='freeBoardNum' value='" + ${freeBoard.freeBoardNum } + "'>";
			str += "</form>";
			
			tr.innerHTML = str;
		}
		
		// 비로그인 시 댓글 작성 X
		function ale(){
			Swal.fire({
				  icon: 'error',
				  title: '로그인 후 이용해 주세요',
				  footer: '<a href="/member/login">로그인</a>'
				})

		}
		
		// 좋아요
		function likes() {
			var likesMap = "${likesMap }";
			// 이미 눌렀으면
			if (likesMap.length > 0) {
				alert("추천을 취소합니다.");
				
				$.ajax({
					  url: "/freeBoard/cancelLikes"
					, type: "post"
					, data: {
						freeBoardNum: $("#freeBoardNum").val()
						,memberId : $("#memberId").val()
					}
					, success: function(data) {
						console.log("추천 삭제");
						$("#rec_count").val(data);					
					}
					, error: function(e) {
						console.log(e);
					}
				}); 
			// 추천 처음 누를 때	
			} else{
				alert("추천을 눌렀습니다.");
				$.ajax({
					  url: "/freeBoard/likes"
					, type: "post"
					, data: {
						freeBoardNum: $("#freeBoardNum").val()
					}
					, success: function(data) {
						console.log("추천 성공");
						$("#likesBtn").prop("disabled", "disabled");
						$("#rec_count").val(data);
					
					}
					, error: function(e) {
						console.log(e);
					}
				}); 
			}
		}
		
		// 해시태그 중복 여부 (안됨)
		function checkHashtag() {
			var hashTagMap = "${hashTagMap }";
			// 이미 들어가 있다면
			if (hashTagMap.length > 0) {
				alert("이미 있는 태그입니다.");
				return false;
			}
			
				console.log("해시태그 추가");
				return true;
		}
		
		
    </script>
    
</body>

</html>