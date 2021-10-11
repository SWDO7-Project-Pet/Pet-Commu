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
                    <h1 class="h3 mb-2 text-gray-800">팁</h1>
                   

                    <!-- 팁 데이터 부분 --> 
                    	<p><a href="/tip/writeTipMain">글 작성하기</a></p>
                       <c:forEach items="${tipList }" var="List">
                       <div class="card shadow mb-4">
                            <div class="card-body">
                            	<div class="table-responsive">                            	
                                	<table class="table table-bordered" id="dataTable">
                                	  <tr>
                                	  	<td>                          	  		
											<div class="container-fluid">
												<!-- 그룹 태그로 role과 aria-multiselectable를 설정한다. -->
												<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
												<!-- 하나의 item입니다. data-parent 설청과 href 설정만 제대로 하면 문제없이 작동합니다. -->
												<div class="panel panel-default">
												<div class="panel-heading" role="tab">
												<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse${List.tipNum }" aria-expanded="false">
													<span style="font-size: 150%">${List.tipTitle }</span>													
												</a>
												<form action="/tip/deleteTip" method="get">
												<input type="hidden" id="tipNum" name="tipNum" value="${List.tipNum }">
												<button type="submit" class="btn btn-primary btn-sm">삭제</button>
												
												<button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal${List.tipNum }">수정</button>
												</form>
												 <!-- Modal -->
												 <form action="/tip/updateTip" method ="post">
												<div class="modal fade" id="myModal${List.tipNum }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
												  <div class="modal-dialog">
												    <div class="modal-content">
												      <div class="modal-header">
												          <div class="form-group">												          
														    <input type="text" class="form-control" id="tipTitle" name="tipTitle" value="${List.tipTitle }">
														  </div>			
												        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
												      </div>
												      <div class="modal-body">
												        <textarea class="form-control" rows="3" id="tipContent" name="tipContent" >${List.tipContent }</textarea>
												      </div>
												      <div class="modal-footer">
												        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
												        <button type="submit" class="btn btn-primary">수정</button>
												      </div>
												    </div>
												  </div>
												</div>				
												<input type="hidden" id="tipNum" name="tipNum" value="${List.tipNum }">
												</form>		
														
														
														
														
														
														
												</div>
												<div id="collapse${List.tipNum }" class="panel-collapse collapse" role="tabpanel">
												<div class="panel-body"><br><br>
													<div style="white-space: pre;"><c:out value="${List.tipContent }"></c:out></div>													
												</div>					
												</div>
												</div>
												</div>
												</div>
                                	  	</td>
                                	  </tr>
                                	</table>        
                                 </div>
                            </div>
                          </div>
                         </c:forEach>         
                    
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

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.html">Logout</a>
                </div>
            </div>
        </div>
    </div>

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
