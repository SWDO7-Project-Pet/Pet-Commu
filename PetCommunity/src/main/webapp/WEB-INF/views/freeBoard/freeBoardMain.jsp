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
	
	    <title>자유 게시판</title>
	
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
	    
	    <style type="text/css">
	    	#hashtags{
	    		
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
                    <h1 class="h3 mb-2 text-gray-800"><span onclick="location.href='freeBoardMain'" style="cursor:pointer;">자유 게시판</span></h1>
                   

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                        	<!-- 글쓰기 버튼 -->
                        	
                            <div class="col-12">                           
                            	<input type="button" class="btn float-right" value="글쓰기" onclick="location.href='freeBoardWrite'">
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr align="center">
                                            <th>글 번호</th>
                                            <th>제목</th>
                                            <th>글쓴이</th>
                                            <th>작성일</th>
                                            <th>조회수</th>
                                            <th>추천</th>                 
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach items="${freeBoardList }" var="freeBoard">
	                                    	<tr>
	                                            <th>${freeBoard.freeBoardNum }</th>
	                                            <th>
	                                            	<!-- 해시태그 -->
	                                            	<form action="/freeBoard/freeBoardfindTag" method="get">
		                                            	<div id="hashtags">                                            		                                            		
			                                            		 <c:forEach items="${allHash }" var="ah">
			                                            			<c:if test="${freeBoard.freeBoardNum == ah.freeBoardNum}">
																		<input type="submit" class="btn btn-info btn-sm" name="searchTag" value="${ah.hashTag }"
																		style="font-size: 1px;">																	
																	</c:if>
																</c:forEach> 														
		                                            	</div>	                                            		
	                                            	</form>
	                                            	<!-- 해시태그 끝 -->
		                                            <a href="/freeBoard/freeBoardRead?freeBoardNum=${freeBoard.freeBoardNum }">
		                                            	${freeBoard.freeBoardTitle } 
		                                            	<c:if test="${freeBoard.freeBoardReplyCount > 0}">
		                                            		<span class="text-warning">[${freeBoard.freeBoardReplyCount }]</span>
		                                            	</c:if>
		                                            </a>
	                                            </th>
	                                            <th>${freeBoard.memberId }</th>
	                                            <th>${freeBoard.freeBoardIndate }</th>
	                                            <th>${freeBoard.freeBoardHits }</th>
	                                            <th>${freeBoard.freeBoardLikes }</th>
	                                        </tr>
                                    	</c:forEach>                                   	
                                    </tbody>
                                </table>                                                          
                            </div>                            
                        </div>                      
                    </div>
					 <!-- 검색 기능 -->
					 <form action="/freeBoard/searchFreeBoard" method="get">
					  <div class="form-row align-items-center">
					    <div class="col-auto my-1">
					      <label class="mr-sm-2 sr-only" for="inlineFormCustomSelect">Preference</label>
					      <select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="condition">
					        <option value="freeBoardTitle">제목</option>
					        <option value="freeBoardContent">내용</option>
					        <option value="memberId">작성자</option>
					      </select>
					    </div>
					    <div class="col-auto my-1">				     
					        <input type="text" class="form-control" id="validationTooltip02" placeholder="검색어 입력.." name="searchWord">					      
					    </div>
					    <div class="col-auto my-1">
					      <button type="submit" class="btn btn-primary">검색</button>
					    </div>
					  </div>
					</form>
					 
                </div>
                <!-- /.container-fluid -->
                              
            </div>
             
        <!-- End of Content Wrapper --
    
    <!-- End of Page Wrapper -->

					<!-- End of Page Wrapper -->
	
	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>
	
	
    <!-- Bootstrap core JavaScript-->
    <script src="../resources/vendor/jquery/jquery.min.js"></script>
    <script src="../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="../resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="../resources/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="../resources/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="../resources/vendor/datatables/jquery.dataTables.js"></script>
    <script src="../resources/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="../resources/js/demo/datatables-demo.js"></script>
    
    <script type="text/javascript">
	    
    </script>

</body>

</html>