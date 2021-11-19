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
	
	    <title>동물백과</title>
	
	    <!-- Custom fonts for this template -->
	    <link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
	    <link
	        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	        rel="stylesheet">
	
	    <!-- Custom styles for this template -->
	    <link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">
	    <link href="/resources/css/sb-admin-2.css" rel="stylesheet">
	
	    <!-- Custom styles for this page -->
	    <link href="/resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
	    
	    <style type="text/css">
		 form{display:inline}
		</style>
	</head>

	<body id="page-top">
		<%@include file="/WEB-INF/views/menubar.jsp"%>

    

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">동물백과</h1>
                   

                    <!-- 동물백과 데이터 부분 --> 
                    
                    <c:if test="${sessionScope.code != null}">
                    	<p><a href="/tip/writeTipAnimal">글 작성하기</a></p>
                    </c:if>
                                                                                                                                                                                                                                                                                                                                                                                                          	 
                     <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary"></h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                            	<span>
                            		<input type="button" value="강아지" id="dog" class="btn btn-primary" onclick="location.href='/tip/tipAnimal?animalKind=강아지';">
                            	</span>                           	
                            	<span>
                            		<input type="button" value="고양이" id="cat" class="btn btn-primary" onclick="location.href='/tip/tipAnimal?animalKind=고양이';">
                            	</span>  
                            	<hr>                         	
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th width="175px">동물사진</th>
                                            <th>동물 품종/설명</th>
                                        </tr>
                                    </thead>
                                   
                                    <tbody>
                                    	 <c:forEach items="${animalList }" var="List">           
                                    		 <tr>                         		
                                    			<td rowspan="2"><img src="/images/${List.animalThumbnailSt }" width="175px" height="150px"></td>
                                    			<td>
                                    				<div>
                                    				<a href="/tip/tipAnimalSpecific?animalNum=${List.animalNum }">${List.animalVariety }</a>                                    				
                                    				<form action="/tip/deleteAnimal" method="get">
													<input type="hidden" id="animalNum" name="animalNum" value="${List.animalNum }">
													<c:if test="${sessionScope.code != null}">
													<button type="submit" class="btn btn-primary">삭제</button>
													</c:if>
													</form>
													<form action="/tip/updateAnimal" method="get">
													<input type="hidden" id="animalNum" name="animalNum" value="${List.animalNum }">
													<c:if test="${sessionScope.code != null}">
													<button type="submit" class="btn btn-primary">수정</button>
													</c:if>
													</form>
													</div>
                                    			</td>                                    			                               
                                    		</tr>
                                    		<tr>
                                    			<td>${List.animalOutline }</td>
                                    		</tr>
                                    	</c:forEach> 
                                       <tr></tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>           
                    
                    
                    
                  </div>

                <!-- /.container-fluid -->

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

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>



    <!-- Bootstrap core JavaScript-->
   <!--  <script src="/resources/vendor/jquery/jquery.min.js"></script>
    <script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script> -->

    <!-- Core plugin JavaScript-->


    <!-- Custom scripts for all pages-->
    


 
	
	
	
</body>


</html>
