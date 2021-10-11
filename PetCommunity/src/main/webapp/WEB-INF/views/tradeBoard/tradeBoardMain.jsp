<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>거래 게시판</title>
		
		<!-- Custom fonts for this template-->
	    <link href="../resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
	    <link
	        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	        rel="stylesheet">
	
	    <!-- Custom styles for this template-->
	    <!-- <link href="resources/css/sb-admin-2.min.css" rel="stylesheet"> -->
	    <link href="../resources/css/sb-admin-2.css" rel="stylesheet">
	    <link href="../resources/css/sb-admin-2.min.css" rel="stylesheet">
	    
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
			
			.image{
				height: 200px;
				width: 100%;
			}
			
			.right{
				text-align: right;
			}
			
			
	    </style>
	    	
	</head>
	<body id="page-top">
	<%@include file="/WEB-INF/views/menubar.jsp"%>
    <!-- Page Wrapper -->
    <div id="wrapper">

       
             <!-- ☆☆☆☆☆☆☆☆ 메인화면 ☆☆☆☆☆☆☆☆ -->   
            <!-- 사진 게시판 화면 --> 
            <div class="container">
			  <input type="button" style="float:right;" class="btn btn-primary" value="글쓰기" onclick="location.href='tradeBoardWrite'">
			  <h1 class="h3 mb-2 text-gray-800"><span onclick="location.href='tradeBoardMain'" style="cursor:pointer;">거래 게시판</span></h1>
			  			
			  		<form action="/tradeBoard/tradeBoardMain" method="get">		 
			  		   <div class="form-row align-items-center" style="float:right;">
					    <div class="col-auto my-1">
					      <label class="mr-sm-2 sr-only" for="inlineFormCustomSelect">Preference</label>
					      <select class="custom-select mr-sm-2" id="searchType" name="condition">
					        <option value="tradeBoardTitle" ${condition eq 'tradeBoardTitle' ? 'selected' : '' }>제목</option>
					        <option value="tradeBoardContent ${condition eq 'tradeBoardContent' ? 'selected' : '' }">내용</option>
					        <option value="memberId" ${condition eq 'memberId' ? 'selected' : '' }>작성자</option>
					      </select>
					    </div>
					    <div class="col-auto my-1">				     
					        <input type="text" class="form-control" id="validationTooltip02" placeholder="검색어 입력.." name="keyword" value="${keyword }">					      
					    </div>
					    <div class="col-auto my-1">
					      <button type="submit" class="btn btn-primary">검색</button>
					    </div>				    
					  </div>
					</form>	  	
			   <!-- 거래 게시판 -->
		        <section class="card-list py-5" id="card-list">   
			        <!-- Divider(메뉴 나누는 선) -->
					<div class="dropdown-divider"></div>  
				  		
		                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center card-list-container">		                   
			                <!-- 카드 -->
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
			                
			                <%-- <c:forEach items="${boardList }" var="tradeBoard">	                    
			                    <div class="col mb-5 post">
			                        <div class="card h-100">
			                            <!-- 썸내일 이미지-->
			                            <img class="image" class="card-img-top" src="/petcommunity/${tradeBoard.TRADEBOARDTHUMBNAILST }" style="cursor:pointer;"
			                             onclick="location.href='tradeBoardRead?tradeBoardNum=' + ${tradeBoard.TRADEBOARDNUM}" />
			                            <!-- Product details-->
			                            <div class="card-body p-4">
			                                <div class="text-center">
			                                    <!-- Product name-->
			                                    <h5 class="fw-bolder">${tradeBoard.TRADEBOARDTITLE }</h5>
			                                    <!-- Product price-->	                                    
			                                </div>
			                            </div>
			                            <!-- Product actions-->
			                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
			                            	<div align="right">${tradeBoard.MEMBERID }</div>
											<div align="right">조회 <span style="font-weight: bold;">${tradeBoard.TRADEBOARDHITS }</span> | ${tradeBoard.TRADEBOARDINDATE }</div>
			                        	</div>
			                    	</div>            
			                	</div>			              	
			                </c:forEach> 	 --%>		                    
		                </div>		              	           
		        </section>
			<!-- /.card-columns -->
			</div>
		</div>
		<!-- /.container -->
				            
			
		<!-- Scroll to Top Button-->
		<a class="scroll-to-top rounded" href="#page-top">
		    <i class="fas fa-angle-up"></i>
		</a>
		    	    
		<!-- Bootstrap core JavaScript-->
	    <script src="../resources/vendor/jquery/jquery.min.js"></script>
	    <script src="../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	
	    <!-- Core plugin JavaScript-->
	    <script src="../resources/vendor/jquery-easing/jquery.easing.min.js"></script>
	
	    <!-- Custom scripts for all pages-->
	    <script src="../resources/js/sb-admin-2.min.js"></script>
	    
	    
	    <script type="text/javascript">
	  	//페이지가 처음 로딩될 때 1page를 보여주기 때문에 초기값을 1로 지정한다.
	    let currentPage=1;
	    //현재 페이지가 로딩중인지 여부를 저장할 변수이다.
	    let isLoading=false;
	    
	   
	    //웹브라우저의 창을 스크롤 할 때 마다 호출되는 함수 등록
	    $(window).on("scroll",function(){
	        //위로 스크롤된 길이
	        let scrollTop=$(window).scrollTop();
	        //웹브라우저의 창의 높이
	        let windowHeight=$(window).height();
	        //문서 전체의 높이
	        let documentHeight=$(document).height();
	        //바닥까지 스크롤 되었는 지 여부를 알아낸다.
	        let isBottom=scrollTop+windowHeight + 10 >= documentHeight;

	        if(isBottom){
	            //만일 현재 마지막 페이지라면
	            if(currentPage == ${totalPageCount} || isLoading){
	                return; //함수를 여기서 끝낸다.
	            }
	            //현재 로딩 중이라고 표시한다.
	            isLoading=true;
	            //로딩바를 띄우고
	            $(".back-drop").show();
	            //요청할 페이지 번호를 1 증가시킨다.
	            currentPage++;
	            //추가로 받아올 페이지를 서버에 ajax 요청을 하고
	            console.log("inscroll"+currentPage);
	            GetList(currentPage);
	        }; 	      	        
	    });
	    
	    const GetList = function(currentPage){
	        console.log("inGetList"+currentPage);
	        

	        // 무한 스크롤
	        $.ajax({
	            url:"ajax_page.do",
	            method:"GET",
	            //검색기능이 있는 경우 condition과 keyword를 함께 넘겨줘야한다. 안그러면 검색결과만 나와야하는데 다른것들이 덧붙여져 나온다.
	            data:"pageNum="+currentPage+"&condition=${condition}&keyword=${keyword}",
	            //ajax_page.jsp의 내용이 data로 들어온다.
	            success:function(data){
	                console.log(data);
	                //응답된 문자열은 html 형식이다.(picture/ajax_page.jsp에 응답내용이 있다.)
	                //해당 문자열을 .card-list-container  div에 html로 해석하라고 추가한다.
	                $(".card-list-container").append(data);
	                //로딩바를 숨긴다.
	                $(".back-drop").hide();
	                //로딩중이 아니라고 표시한다.
	                isLoading=false;
	                console.log("ajax");
	                
	                
	            }
	        });
	    }
	    </script>
	</body>
</html>

