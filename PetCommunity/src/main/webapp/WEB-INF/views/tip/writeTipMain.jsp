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
	
	    <title>팁</title>
	
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
	</head>

	<body id="page-top">

    <%@include file="/WEB-INF/views/menubar.jsp"%>
                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">팁 작성</h1>
                   

                    <!-- 팁 데이터 작성 부분 --> 
                      <form action="/tip/writeTipMain" method ="post" > <!-- enctype없으면 파일이 전송이안됨 -->                                                                                                                                                                                                                                                                                                                                                                                            	 
                   		  <div class="form-group">
						    <label for="exampleInputPassword1">팁 제목</label>
						    <input type="text" class="form-control" id="tipTitle" name="tipTitle" placeholder="ex)애완상식">
						  </div>					 
						  <label for="exampleInputEmail1">팁 내용</label>
						  <textarea class="form-control" rows="3" id="tipContent" name="tipContent"></textarea>
						  
						  <button type="submit" class="btn btn-default">작성</button>
                    </form>                            	 
                               
                    
                    
                    
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
    <script src="/resources/vendor/jquery/jquery.min.js"></script>
    <script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/resources/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="/resources/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="/resources/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="/resources/js/demo/datatables-demo.js"></script>

</body>

</html>
