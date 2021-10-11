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
	    <link href="../resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
	    <link
	        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	        rel="stylesheet">
	
	    <!-- Custom styles for this template -->
	    <link href="../resources/css/sb-admin-2.min.css" rel="stylesheet">
	    <link href="../resources/css/sb-admin-2.css" rel="stylesheet">
	
	    <!-- Custom styles for this page -->
	    <link href="../resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
	</head>

	<body id="page-top">

   	<%@include file="/WEB-INF/views/menubar.jsp"%>

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">동물백과</h1>
                   

                    <!-- 동물백과 데이터 부분 -->                                                                                                                                                                                                                                                                                                                                                                     	 
                     <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary"></h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    	<tr>                                    		
                                    		<td colspan="6" style="text-align: center;">
                                    			<c:forEach items="${photo }" var="pt">
                                    				<img src="/images/${pt.adBoardPhotoSt }" width="200px" height="200px">
                                    			</c:forEach>
                                    		</td>                                    		
                                    	</tr>
                                    	<tr>                                    	
                           					<th> 품종 </th>
                           					 <td colspan="5">${animal.animalVariety }</td>
                        				</tr>
                                    	<tr>                                    	
                           					<th> 원산지 </th>
                           					 <td colspan="5">${animal.animalOrigin }</td>
                        				</tr>
                                    	<tr>                                    	
                           					<th> 크기 </th>
                           					 <td>${animal.animalSize }</td>
                           					<th> 체고 </th>
                           					 <td>${animal.animalHeight }</td>
                           					<th> 체중 </th>
                           					 <td>${animal.animalWeight }</td>
                        				</tr>
                                    	<tr>                                    	
                           					<th> 외모 </th>
                           					 <td colspan="5">${animal.animalAppearance }</td>
                        				</tr>
                                    	<tr>                                    	
                           					<th> 색상 </th>
                           					 <td colspan="5">${animal.animalColor }</td>
                        				</tr>
                                    	<tr>                                    	
                           					<th> 성격 </th>
                           					 <td colspan="5">${animal.animalPersonality }</td>
                        				</tr>
                                    	<tr>                                    	
                           					<th> 용도 </th>
                           					 <td colspan="5">${animal.animalPurpose }</td>
                        				</tr>
                                    	<tr>                                    	
                           					<th> 유의할 질병 </th>
                           					 <td colspan="5">${animal.animalDisease }</td>
                        				</tr>
                                    	<tr>                                    	
                           					<th> 추천성향 </th>
                           					 <td colspan="5">${animal.animalRecommend }</td>
                        				</tr>
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
    <script src="../resources/vendor/jquery/jquery.min.js"></script>
    <script src="../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="../resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="../resources/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="../resources/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="../resources/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="../resources/js/demo/datatables-demo.js"></script>

</body>

</html>
