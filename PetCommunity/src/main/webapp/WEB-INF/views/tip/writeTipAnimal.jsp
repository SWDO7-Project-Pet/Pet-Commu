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
	    <script type="text/javascript">
			function formCheck(){
				var uploadFile = document.getElementById("uploadFile").files[0];
				var fileName = uploadFile.name;
				var fileSize = uploadFile.size;
				
				//console.log(uploadFile);
				//console.log(fileName);
				//console.log(fileSize);
				
				var ext = fileName.substring(fileName.lastIndexOf("."),fileName.length);
				
				//console.log(extension);
				
				if(ext == ".jpg" || ext == ".jpeg" || ext == ".png" || ext ==".jfif"){
					return true;					
				}else{
					alert("파일 확장자를 확인해주세요(jpg, jpeg, png, gif,jfif 업로드 가능)");				
					return false;
				}				
			}
		</script>
	    
	</head>

	<body id="page-top">

 	<%@include file="/WEB-INF/views/menubar.jsp"%>

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">동물백과</h1>
                   

                    <!-- 동물백과 작성  부분 --> 
					<form action="/tip/writeTipAnimal" method ="post" enctype="multipart/form-data"  onsubmit="return formCheck();"> <!-- enctype없으면 파일이 전송이안됨 -->                                                                                                                                                                                                                                                                                                                                                                                            	 
                   		<div class="form-group">
						    <label for="exampleInputEmail1">애완동물 종류</label>
						    <input type="text" class="form-control" id="animalKind" name="animalKind" placeholder="ex)강아지">
						  </div>
						  <div class="form-group">
						    <label for="exampleInputPassword1">종 이름</label>
						    <input type="text" class="form-control" id="animalVariety" name="animalVariety" placeholder="ex)치와와">
						  </div>
						  <div class="form-group">
						    <label for="exampleInputFile">애완동물 썸네일 사진 업로드</label>
						    <input type="file"  id ="uploadThumbNail" name="uploadThumbNail">						   
						  </div>
						  <div class="form-group">
						    <label for="exampleInputFile">애완동물 사진 업로드</label>
						    <input type="file"  multiple="multiple" id ="uploadFile" name="uploadFile">						   
						  </div>							 
						  <label for="exampleInputEmail1">썸네일설명</label>
						  <textarea class="form-control" rows="3" id="animalOutline" name="animalOutline"></textarea>
						  
						  <div class="form-group">
						    <label for="exampleInputPassword1">원산지</label>
						    <input type="text" class="form-control" id="animalOrigin" name="animalOrigin" placeholder="ex)영국">
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputPassword1">크기</label>
						    <input type="text" class="form-control" id="animalSize" name="animalSize" placeholder="ex)소형견">
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputPassword1">체고</label>
						    <input type="text" class="form-control" id="animalHeight" name="animalHeight" placeholder="ex)30~45cm">
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputPassword1">체중</label>
						    <input type="text" class="form-control" id="animalWeight" name="animalWeight" placeholder="ex)3~4kg">
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputPassword1">외모</label>
						    <input type="text" class="form-control" id="animalAppearance" name="animalAppearance" placeholder="ex)듬직하다">
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputPassword1">색상</label>
						    <input type="text" class="form-control" id="animalColor" name="animalColor" placeholder="ex)흰색">
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputPassword1">성격</label>
						    <input type="text" class="form-control" id="animalPersonality" name="animalPersonality" placeholder="ex)활발하다">
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputPassword1">용도</label>
						    <input type="text" class="form-control" id="animalPurpose" name="animalPurpose" placeholder="ex)애완견">
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputPassword1">유의할 질병</label>
						    <input type="text" class="form-control" id="animalDisease" name="animalDisease" placeholder="ex)슬개골탈구">
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputPassword1">추천성향</label>
						    <input type="text" class="form-control" id="animalRecommend" name="animalRecommend" placeholder="ex)아파트, 젊은층">
						  </div>
						  
						  <button type="submit" class="btn btn-primary btn-sm">작성</button>
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
