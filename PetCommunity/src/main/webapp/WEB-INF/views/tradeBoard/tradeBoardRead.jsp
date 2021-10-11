<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <meta name="description" content="">
	    <meta name="author" content="">
	
	    <title>${tradeBoard.tradeBoardTitle }</title>
	
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
		
		<style type="text/css">
			#tradeImg{
				height: 420px;
				width: 720px;
			}
			.readimage{
				height: 420px;
				width: 100%;
			}
			#tra{
				width: 720px;			
			}
			
		</style>
		
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
					
										
					 <div class="col mb-5" id="tra" style="float: none; margin:0 auto;">
					 		<span>
								<input type="button" value="목록으로" class="btn float-right" onclick="location.href='tradeBoardMain'">
								<!-- 작성자와 로그인된 아이디가 같다면 -->
								<c:if test="${sessionScope.memberId == tradeBoard.memberId || sessionScope.code != null}">
									<input type="button" value="수정" class="btn float-right" onclick="location.href='tradeBoardUpdate/?tradeBoardNum=${tradeBoard.tradeBoardNum }';">
									<input type="button" value="삭제" class="btn float-right" onclick="deleteCheck();">													
								</c:if>					
							</span>	
					 		<h1>거래 게시판</h1>
					 		
		                        <div class="card bg-light mb-3">
		                            <!-- 내용 이미지-->
		                            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
		                              
		                              <!-- 밑에 땡땡이 -->
		                              <ol class="carousel-indicators">
										<li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
										<c:forEach var="i" begin="1" end="${fn:length(tradePhotoList) }">
									    	<li data-target="#carouselExampleIndicators" data-slide-to="${i }"></li>
									    </c:forEach>
									  </ol> 
									  <!-- 이미지 -->								  
									  <div class="carousel-inner">								    
									    <!-- 썸네일 이미지 -->
									    <div class="carousel-item active">
									      <img src="/petcommunity/${tradeBoard.tradeBoardThumbnailSt }" class="d-block w-100 readimage" alt="...">
									    </div>
									    <!-- 본문 이미지 -->
									    <c:forEach items="${tradePhotoList }" var="tradePhoto" varStatus="vs">									    			    																			
										    <div class="carousel-item">
										      <img src="/petcommunity/${tradePhoto.tradeBoardPhotoSt }" class="d-block w-100 readimage" alt="...">
										    </div>	
									    </c:forEach>								    
									  </div>
								 
									  <!-- 오른쪽,왼쪽 버튼 -->
									  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
									    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
									    <span class="sr-only">Previous</span>
									  </a>
									  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
									    <span class="carousel-control-next-icon" aria-hidden="true"></span>
									    <span class="sr-only">Next</span>
									  </a>
									</div>
		                            <!-- Product details-->
		                            <div class="card-body p-4">
		                                <div class="text-center">
		                                    <!-- Product name-->
		                                    <h1 class="fw-bolder">${tradeBoard.tradeBoardTitle }</h1>
		                                    <!-- Product price-->
		                                    <h3 align="right">${tradeBoard.memberId }</h3>
		                                    <div align="right">조회 ${tradeBoard.tradeBoardHits } | ${tradeBoard.tradeBoardIndate }</div>
		                                </div>
		                            </div>
		                            <!-- Product actions-->
		                            <div class="card-footer">
										${tradeBoard.tradeBoardContent }
		                        	</div>
		                    	</div> 
		                    	<!-- 로그인 아이디와 작성자 아이디가 다를시만 보여주기 -->
		                    	<c:if test="${tradeBoard.memberId != sessionScope.memberId }">
			                    	<div align="center">
			                    		<button type="button" class="btn btn-primary" onclick="location.href='/chat'">거래 신청</button>		
			                    	</div>     
		                    	</c:if>               	           
		                </div>
					
						
					     <!-- 글 삭제 폼 -->
		                 <form id="deleteForm" action="/tradeBoard/tradeBoardDelete" method="post">
							<input type="hidden" name="tradeBoardNum" value="${tradeBoard.tradeBoardNum }">
						 </form>
					</div>
                   
    
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

					<!-- End of Page Wrapper -->
	
	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

    <!-- Bootstrap core JavaScript-->
    <script src="/resources/vendor/jquery/jquery.min.js"></script>
    <script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/resources/js/sb-admin-2.min.js"></script>
    
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
    			
		// 비로그인 시 댓글 작성 X
		function ale(){
			Swal.fire({
				  icon: 'error',
				  title: '로그인 후 이용해 주세요',
				  footer: '<a href="/member/login">로그인</a>'
				})

		}	
    </script>
    
</body>

</html>