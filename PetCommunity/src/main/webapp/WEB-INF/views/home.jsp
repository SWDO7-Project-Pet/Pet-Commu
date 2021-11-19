<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport"
			content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="">
		<meta name="author" content="">
		
		<title>타이를</title>
		
		<!-- Custom fonts for this template-->
		<link href="/resources/vendor/fontawesome-free/css/all.min.css"
			rel="stylesheet" type="text/css">
		<link
			href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
			rel="stylesheet">
		
		<!-- Custom styles for this template-->
		<link href="/resources/css/sb-admin-2.css" rel="stylesheet">
		<link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

		
		<style type="text/css">
			.carousel {
			  width : 99%;			  
			}			
		</style>
	
	</head>
	
	<body id="page-top">	
		<%@include file="/WEB-INF/views/menubar.jsp"%>
		
		<!-- Page Wrapper -->
		<div id="wrapper">	
					<!-- 메인 홈페이지 -->
					<!-- Page Heading -->
					<div class="container-fluid">
					
						<!-- 캐러셀 -->
						<div class="container row" style="margin:0 auto;">
							<div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
							  <ol class="carousel-indicators">
							    <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
							    <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
							    <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
							  </ol>
							  <div class="carousel-inner">
							    <div class="carousel-item active">
							      <img src="/resources/img/banner/banner1.jpg" class="d-block w-100" alt="..." style="cursor:pointer;height: 400px">
							    </div>
							    <div class="carousel-item">
							      <img src="/resources/img/banner/banner2.jpg" class="d-block w-100" alt="..." style="cursor:pointer;height: 400px"
							      onclick="location.href='https://www.animal.go.kr/front/index.do'">
							    </div>
							    <div class="carousel-item">
							      <img src="/resources/img/banner/banner3.jpg" class="d-block w-100" alt="..." style="cursor:pointer;height: 400px"
							      onclick="location.href='https://www.coupang.com/np/search?q=%EB%B0%98%EB%A0%A4%EB%8F%99%EB%AC%BC&channel=relate'">
							    
							    </div>
							  </div>
							  <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
							    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
							    <span class="sr-only">Previous</span>
							  </a>
							  <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
							    <span class="carousel-control-next-icon" aria-hidden="true"></span>
							    <span class="sr-only">Next</span>
							  </a>
							</div>						
						</div>	
						
						<br><br>
						
					<div class="container row" style="margin:0 auto; float: none;">
                    	                    	
                    	<div class="list-group col-sm-6">
                    	<h2><i class="fa fa-star" aria-hidden="true"></i>인기글</h2>                     	
                    	<c:forEach items="${bestFreeBoardList }" var="best" begin="0" end="9">                 	
						  <a href="/freeBoard/freeBoardRead?freeBoardNum=${best.freeBoardNum }" class="list-group-item list-group-item-action">
						  	${best.freeBoardTitle }
						  	<span class="pull-right">${best.memberId }</span>
						  </a>					  
						</c:forEach>
						</div> 
					        
					    <div class="list-group col-sm-6">
                    	<h2><i class="fas fa-comments"></i> 최신글</h2>  
						  <c:forEach items="${newFreeBoardList }" var="newfree" begin="0" end="9">                 	
						  <a href="/freeBoard/freeBoardRead?freeBoardNum=${newfree.freeBoardNum }" class="list-group-item list-group-item-action">
						  	${newfree.freeBoardTitle }
						  	<span class="pull-right">${newfree.memberId }</span>
						  </a>					  
						</c:forEach>
						</div>               
                    </div>

					
                </div>		
                	
                
			</div>

			<!-- 개인정보 취급 모달 -->
			<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLabel">개인정보</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			        <GBG>은(는) 개인정보보호법에 따라 이용자의 개인정보 보호 및 권익을 보호하고 개인정보와 관련한 이용자의 고충을 원활하게 처리할 수 있도록 다음과 같은 처리방침을 두고 있습니다.
			        <br><br>
			        <GBG>은(는) 회사는 개인정보처리방침을 개정하는 경우 웹사이트 공지사항(또는 개별공지)을 통하여 공지할 것입니다.
					<br><br>
					 1. 개인정보의 처리 목적 <GBG>은(는) 개인정보를 다음의 목적을 위해 처리합니다. 처리한 개인정보는 다음의 목적이외의 용도로는 사용되지 않으며 이용 목적이 변경될 시에는 사전동의를 구할 예정입니다.
					<br><br>
					 2. 개인정보 파일 현황
					 <br>
					 기타 개인정보침해에 대한 신고나 상담이 필요하신 경우에는 아래 기관에 문의하시기 바랍니다.
					 <br>
					 - 개인정보침해신고센터 (privacy.kisa.or.kr / 국번없이 118)
				     <br>
					 - 대검찰청 사이버범죄수사단 (www.spo.go.kr / 02-3480-3571)
					 <br>
					 - 경찰청 사이버안전국 (www.ctrc.go.kr / 국번없이 182)

								      
			      
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
			      </div>
			    </div>
			  </div>
			</div>
			
			<!-- 신고방법 모달 -->
			<div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel2" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLabel">신고방법</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			        ...
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
			      </div>
			    </div>
			  </div>
			</div>
			


			<br><br>
			<!-- Footer -->
            <footer class="sticky-footer bg-white">
            	<div class="row" >
				    <div class="col-sm-9">
				        <div style="float: left; margin-right: 10px;height:100px;">
				        	<img src="/resources/img/logo.png" alt="로고" style="width: 60px;height: 60px;">
				        </div>
				        <div>
				            <a href="#" data-toggle="modal" data-target="#exampleModal">개인정보보호</a>
				            | <a href="#">광고문의</a>
				            | <a href="#" data-toggle="modal" data-target="#exampleModal2">신고방법</a>
				            | <a href="https://github.com/SWDO7-Project-Pet/MainProject" target="_blank">Github</a>  v1.5.4
				            <hr style="margin: 0px 0;"/>
				            <strong>상호명</strong> : 미정 | <strong>대표명</strong> : 미정 
				            <br/> @ 2021 Please donate for your pet
				        </div>
				    </div>			    
				</div>
            </footer>
	
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
	
					<!-- Page level plugins -->
					<script src="/resources/vendor/chart.js/Chart.min.js"></script>	
	</body>

</html>